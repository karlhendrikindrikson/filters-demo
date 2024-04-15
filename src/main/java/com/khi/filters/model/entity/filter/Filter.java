package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "FILTER")
public class Filter extends AbstractJPAEntity<Long> {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "filter", cascade = ALL, orphanRemoval = true)
    private Set<FilterCriteria> criteria;

    public Filter() {}

    public Filter(Long id, Instant created, String name) {
        super(id, created);
        this.name = name;
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
}
