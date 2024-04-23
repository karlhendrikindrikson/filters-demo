package com.khi.filters.controller.model.filter;

import com.khi.filters.model.entity.filter.Filter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterDTO {
    private Long id;
    private String name;
    private List<FilterCriteriaDTO> criteria = List.of();

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
                .map(FilterCriteriaDTO::fromModel)
                .toList()
        );

        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterDTO filterDTO = (FilterDTO) o;
        return Objects.equals(getId(), filterDTO.getId()) &&
            Objects.equals(getName(), filterDTO.getName()) &&
            Objects.equals(getCriteria(), filterDTO.getCriteria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCriteria());
    }
}
