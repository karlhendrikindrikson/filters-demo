package com.khi.filters.model.entity.filter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
public abstract class AbstractJPAEntity<T extends Serializable> {

    public static final String GENERATOR_NAME = "seq_generator";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = GENERATOR_NAME)
    protected T id;

    @Column(updatable = false, nullable = false)
    protected Instant created = Instant.now();

    public AbstractJPAEntity() {}

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
