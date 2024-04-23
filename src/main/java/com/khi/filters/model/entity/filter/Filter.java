package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import static com.khi.filters.model.entity.filter.AbstractJPAEntity.GENERATOR_NAME;
import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "FILTER")
@SequenceGenerator(name = GENERATOR_NAME, sequenceName = "FILTER_id_seq", allocationSize = 1)
public class Filter extends AbstractJPAEntity<Long> {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "filter", cascade = ALL, orphanRemoval = true)
    private Set<FilterCriteria> criteria;

    public Filter() {}

    public Filter(Long id, Instant created, String name, Set<FilterCriteria> criteria) {
        this.id = id;
        this.created = created;
        this.name = name;
        this.criteria = criteria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FilterCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(Set<FilterCriteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return getId().equals(filter.getId()) &&
            getName().equals(filter.getName()) &&
            getCriteria().equals(filter.getCriteria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCriteria());
    }
}
