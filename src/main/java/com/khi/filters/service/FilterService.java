package com.khi.filters.service;

import com.khi.filters.controller.model.filter.FilterCriteriaDTO;
import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.exception.ValidationException;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.model.entity.filter.FilterCriteria;
import com.khi.filters.repository.FilterRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterService {

    private final FilterRepository repository;

    public FilterService(FilterRepository repository) {
        this.repository = repository;
    }

    public List<Filter> findAll() {
        return repository.findAllByOrderByCreatedDesc();
    }

    @Transactional
    public Filter create(FilterDTO data) throws ValidationException {
        if (data.getCriteria().isEmpty()) throw new ValidationException("No criteria provided");

        Filter filter = new Filter();

        filter.setName(data.getName());
        filter.setCriteria(processCriteria(data.getCriteria(), filter));

        return repository.saveAndFlush(filter);
    }

    private Set<FilterCriteria> processCriteria(List<FilterCriteriaDTO> data, Filter filter) {
        return data.stream()
            .map((criteriaData) -> {
                FilterCriteria criteria = new FilterCriteria();

                criteria.setCriteriaType(criteriaData.getCriteriaType());
                criteria.setCriteriaCondition(criteriaData.getCriteriaCondition());
                criteria.setValue(criteriaData.getValue());
                criteria.setFilter(filter);

                validateFilterCriteria(criteria);

                return criteria;
            })
            .collect(Collectors.toSet());
    }

    private void validateFilterCriteria(FilterCriteria criteria) {
        if (!criteria.getCriteriaCondition().getCriteriaTypes().contains(criteria.getCriteriaType())) {
            throw new ValidationException(
                "Criteria condition '%s' cannot be used with criteria type '%s'"
                    .formatted(criteria.getCriteriaCondition(), criteria.getCriteriaType())
            );
        }

        switch (criteria.getCriteriaType()) {
            case DATE -> {
                try {
                    LocalDate.parse(criteria.getValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                } catch (Exception e) {
                    throw new ValidationException("Invalid date format '%s'".formatted(criteria.getValue()));
                }
            }

            case AMOUNT -> {
                if (!NumberUtils.isCreatable(criteria.getValue())) {
                    throw new ValidationException(
                        "Invalid amount '%s'. Criteria value must be a valid number".formatted(criteria.getValue())
                    );
                }
            }
        }
    }
}
