package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class AbstractJPAEntity<T extends Serializable> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected T id = null;

    @Column(updatable = false, insertable = false, nullable = false)
    protected Instant created;

    public AbstractJPAEntity() {}

    public AbstractJPAEntity(T id, Instant created) {
        this.id = id;
        this.created = created;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
