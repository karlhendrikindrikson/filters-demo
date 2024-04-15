package com.khi.filters.service;

import com.khi.filters.controller.model.filter.FilterCriteriaDTO;
import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.model.entity.filter.FilterCriteria;
import com.khi.filters.repository.FilterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterService {

    private final FilterRepository repository;

    public FilterService(FilterRepository repository) {
        this.repository = repository;
    }

    public List<Filter> findAllFilters() {
        return repository.findAll();
    }

    @Transactional
    public Filter save(FilterDTO data) {
        if (data.getId() == null) {
            return createFilter(data);
        } else {
            return updateFilter(data);
        }
    }

    private Filter createFilter(FilterDTO data) {
        Filter filter = new Filter();

        filter.setName(data.getName());
        filter.setCriteria(processCriteria(data.getCriteria(), filter));

        return repository.saveAndFlush(filter);
    }

    private Filter updateFilter(FilterDTO data) {
        Filter filter = repository.findById(data.getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid filter id ('%s')".formatted(data.getId())));

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

                    return criteria;
                })
                .collect(Collectors.toSet());
    }
}
