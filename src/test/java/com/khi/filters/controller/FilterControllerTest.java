package com.khi.filters.controller;

import com.khi.filters.controller.model.filter.FilterCriteriaDTO;
import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.model.entity.filter.CriteriaCondition;
import com.khi.filters.model.entity.filter.CriteriaType;
import com.khi.filters.service.FilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.khi.filters.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FilterControllerTest {

    @Mock
    private FilterService service;

    @InjectMocks
    private FilterController controller;

    @Test
    public void findAll_returns_2_filters_when_service_returns_2_filters() {
        doReturn(List.of(MOCK_FILTER_1, MOCK_FILTER_2)).when(service).findAll();

        List<FilterDTO> result = controller.findAll();

        assertThat(result).containsExactly(MOCK_FILTER_1_DTO, MOCK_FILTER_2_DTO);

        verify(service).findAll();
    }

    @Test
    public void findAll_returns_0_filters_when_service_returns_0_filters() {
        doReturn(List.of()).when(service).findAll();

        List<FilterDTO> result = controller.findAll();

        assertThat(result).isEmpty();

        verify(service).findAll();
    }

    @Test
    public void create_returns_created_filter() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");
        data.setCriteria(
            List.of(
                new FilterCriteriaDTO(
                    CriteriaType.AMOUNT,
                    CriteriaCondition.GREATER_THAN,
                    "30"
                )
            )
        );

        doReturn(MOCK_FILTER_1).when(service).create(data);

        FilterDTO result = controller.create(data);

        assertThat(result).isEqualTo(MOCK_FILTER_1_DTO);

        verify(service).create(data);
    }
}
