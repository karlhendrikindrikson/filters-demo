package com.khi.filters.controller.model.filter;

import com.khi.filters.model.entity.filter.CriteriaCondition;
import com.khi.filters.model.entity.filter.CriteriaType;
import com.khi.filters.model.entity.filter.FilterCriteria;

import java.util.Objects;

public class FilterCriteriaDTO {
    private CriteriaType criteriaType;
    private CriteriaCondition criteriaCondition;
    private String value;

    public FilterCriteriaDTO() {}

    public FilterCriteriaDTO(CriteriaType criteriaType, CriteriaCondition criteriaCondition, String value) {
        this.criteriaType = criteriaType;
        this.criteriaCondition = criteriaCondition;
        this.value = value;
    }

    public CriteriaType getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(CriteriaType criteriaType) {
        this.criteriaType = criteriaType;
    }

    public CriteriaCondition getCriteriaCondition() {
        return criteriaCondition;
    }

    public void setCriteriaCondition(CriteriaCondition criteriaCondition) {
        this.criteriaCondition = criteriaCondition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static FilterCriteriaDTO fromModel(FilterCriteria filterCriteria) {
        FilterCriteriaDTO dto = new FilterCriteriaDTO();

        dto.setCriteriaType(filterCriteria.getCriteriaType());
        dto.setCriteriaCondition(filterCriteria.getCriteriaCondition());
        dto.setValue(filterCriteria.getValue());

        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterCriteriaDTO that = (FilterCriteriaDTO) o;
        return getCriteriaType() == that.getCriteriaType() &&
            getCriteriaCondition() == that.getCriteriaCondition() &&
            Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCriteriaType(), getCriteriaCondition(), getValue());
    }
}
