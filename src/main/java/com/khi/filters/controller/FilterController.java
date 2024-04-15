package com.khi.filters.controller;

import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("filter")
public class FilterController {

    @Autowired
    private FilterService service;

    @GetMapping
    public List<FilterDTO> findAll() {
        return service.findAllFilters()
                .stream()
                .map(FilterDTO::fromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FilterDTO save(@RequestBody FilterDTO data) {
        Filter filter = service.save(data);
        return FilterDTO.fromModel(filter);
    }
}
