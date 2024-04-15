package com.khi.filters.controller.model.filter;

import com.khi.filters.model.entity.filter.Filter;

import java.util.List;
import java.util.stream.Collectors;

public class FilterDTO {
    private Long id;
    private String name;
    private List<FilterCriteriaDTO> criteria;

    public FilterDTO() {}

    public FilterDTO(Long id, String name, List<FilterCriteriaDTO> criteria) {
        this.id = id;
        this.name = name;
        this.criteria = criteria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilterCriteriaDTO> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<FilterCriteriaDTO> criteria) {
        this.criteria = criteria;
    }

    public static FilterDTO fromModel(Filter filter) {
        FilterDTO dto = new FilterDTO();

        dto.setId(filter.getId());
        dto.setName(filter.getName());
        dto.setCriteria(
                filter.getCriteria()
                        .stream()
                        .map((criteria) -> {
                            FilterCriteriaDTO criteriaDTO = new FilterCriteriaDTO();

                            criteriaDTO.setCriteriaType(criteria.getCriteriaType());
                            criteriaDTO.setCriteriaCondition(criteria.getCriteriaCondition());
                            criteriaDTO.setValue(criteria.getValue());

                            return criteriaDTO;
                        })
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
