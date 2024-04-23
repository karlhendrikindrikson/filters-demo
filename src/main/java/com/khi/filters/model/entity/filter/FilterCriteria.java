package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

import static com.khi.filters.model.entity.filter.AbstractJPAEntity.GENERATOR_NAME;
import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "FILTER_CRITERIA")
@SequenceGenerator(name = GENERATOR_NAME, sequenceName = "FILTER_CRITERIA_id_seq", allocationSize = 1)
public class FilterCriteria extends AbstractJPAEntity<Long> {

    @Column(nullable = false)
    @Enumerated(ORDINAL)
    private CriteriaType criteriaType;

    @Column(nullable = false)
    @Enumerated(ORDINAL)
    private CriteriaCondition criteriaCondition;

    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = LAZY)
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
        this.id = id;
        this.created = created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterCriteria that = (FilterCriteria) o;
        return getId().equals(that.getId()) &&
            getCriteriaType().equals(that.getCriteriaType()) &&
            getCriteriaCondition().equals(that.getCriteriaCondition()) &&
            getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCriteriaType(), getCriteriaCondition(), getValue());
    }
}
