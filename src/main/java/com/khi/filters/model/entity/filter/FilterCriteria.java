package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.time.Instant;

import static jakarta.persistence.EnumType.ORDINAL;

@Entity
@Table(name = "FILTER_CRITERIA")
public class FilterCriteria extends AbstractJPAEntity<Long> {

    @Column(nullable = false)
    @Enumerated(ORDINAL)
    private CriteriaType criteriaType;

    @Column(nullable = false)
    @Enumerated(ORDINAL)
    private CriteriaCondition criteriaCondition;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "filterId", referencedColumnName = "id")
    private Filter filter;

    public FilterCriteria() {}

    public FilterCriteria(
        Long id,
        Instant created,
        CriteriaType criteriaType,
        CriteriaCondition criteriaCondition,
        String value
    ) {
        super(id, created);
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

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
