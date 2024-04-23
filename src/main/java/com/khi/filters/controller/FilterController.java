package com.khi.filters.controller;

import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("filters")
public class FilterController {

    @Autowired
    private FilterService service;

    @GetMapping
    public List<FilterDTO> findAll() {
        return service.findAll()
                .stream()
                .map(FilterDTO::fromModel)
                .toList();
    }

    @PostMapping
    public FilterDTO create(@RequestBody FilterDTO data) {
        Filter filter = service.create(data);
        return FilterDTO.fromModel(filter);
    }
}
