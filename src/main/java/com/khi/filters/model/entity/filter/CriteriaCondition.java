package com.khi.filters.model.entity.filter;

import java.util.List;

import static com.khi.filters.model.entity.filter.CriteriaType.AMOUNT;
import static com.khi.filters.model.entity.filter.CriteriaType.DATE;
import static com.khi.filters.model.entity.filter.CriteriaType.TITLE;

public enum CriteriaCondition {
    EQUALS(List.of(AMOUNT, DATE, TITLE)),
    NOT_EQUALS(List.of(AMOUNT, DATE, TITLE)),
    GREATER_THAN(List.of(AMOUNT)),
    GREATER_THAN_OR_EQUALS(List.of(AMOUNT)),
    LESS_THAN(List.of(AMOUNT)),
    LESS_THAN_EQUALS(List.of(AMOUNT)),
    STARTS_WITH(List.of(TITLE)),
    ENDS_WITH(List.of(TITLE)),
    CONTAINS(List.of(TITLE)),
    FROM(List.of(DATE)),
    TO(List.of(DATE)),
    ;

    private final List<CriteriaType> criteriaTypes;

    CriteriaCondition(List<CriteriaType> criteriaTypes) {
        this.criteriaTypes = criteriaTypes;
    }

    public List<CriteriaType> getCriteriaTypes() {
        return criteriaTypes;
    }
}
