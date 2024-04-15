package com.khi.filters.controller.model.filter;

import com.khi.filters.model.entity.filter.CriteriaCondition;
import com.khi.filters.model.entity.filter.CriteriaType;

public class FilterCriteriaDTO {
    private Long id;
    private CriteriaType criteriaType;
    private CriteriaCondition criteriaCondition;
    private String value;

    public FilterCriteriaDTO() {}

    public FilterCriteriaDTO(Long id, CriteriaType criteriaType, CriteriaCondition criteriaCondition, String value) {
        this.id = id;
        this.criteriaType = criteriaType;
        this.criteriaCondition = criteriaCondition;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
