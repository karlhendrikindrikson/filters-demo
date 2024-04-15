package com.khi.filters.repository;

import com.khi.filters.model.entity.filter.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {}
