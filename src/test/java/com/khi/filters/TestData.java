package com.khi.filters;

import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.model.entity.filter.CriteriaCondition;
import com.khi.filters.model.entity.filter.CriteriaType;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.model.entity.filter.FilterCriteria;

import java.time.Instant;
import java.util.Set;

public class TestData {

    public static final Instant MOCK_INSTANT_1 = Instant.parse("2024-04-18T10:11:21.00Z");
    public static final Instant MOCK_INSTANT_2 = Instant.parse("2024-04-18T14:02:05.00Z");

    public static final FilterCriteria MOCK_FILTER_CRITERIA_1 = new FilterCriteria(
        1L,
        MOCK_INSTANT_1,
        CriteriaType.TITLE,
        CriteriaCondition.STARTS_WITH,
        "EX_"
    );
    public static final FilterCriteria MOCK_FILTER_CRITERIA_2 = new FilterCriteria(
        2L,
        MOCK_INSTANT_2,
        CriteriaType.DATE,
        CriteriaCondition.FROM,
        "01/01/2024"
    );
    public static final FilterCriteria MOCK_FILTER_CRITERIA_3 = new FilterCriteria(
        3L,
        MOCK_INSTANT_2,
        CriteriaType.DATE,
        CriteriaCondition.TO,
        "12/31/2024"
    );

    public static final Filter MOCK_FILTER_1 = new Filter(
        1L,
        MOCK_INSTANT_1,
        "Exercise Title",
        Set.of(MOCK_FILTER_CRITERIA_1)
    );
    public static final Filter MOCK_FILTER_2 = new Filter(
        2L,
        Instant.parse("2024-04-18T00:00:00.00Z"),
        "Year 2024",
        Set.of(MOCK_FILTER_CRITERIA_2, MOCK_FILTER_CRITERIA_3)
    );

    public static final FilterDTO MOCK_FILTER_1_DTO = FilterDTO.fromModel(MOCK_FILTER_1);
    public static final FilterDTO MOCK_FILTER_2_DTO = FilterDTO.fromModel(MOCK_FILTER_2);
}
