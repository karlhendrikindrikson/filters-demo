package com.khi.filters.service;

import com.khi.filters.controller.model.filter.FilterCriteriaDTO;
import com.khi.filters.controller.model.filter.FilterDTO;
import com.khi.filters.exception.ValidationException;
import com.khi.filters.model.entity.filter.CriteriaCondition;
import com.khi.filters.model.entity.filter.CriteriaType;
import com.khi.filters.model.entity.filter.Filter;
import com.khi.filters.model.entity.filter.FilterCriteria;
import com.khi.filters.repository.FilterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.khi.filters.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {

    @Mock
    private FilterRepository repository;

    @InjectMocks
    private FilterService service;

    @Test
    public void findAll_returns_2_filters_if_repository_returns_2_filters() {
        doReturn(List.of(MOCK_FILTER_1, MOCK_FILTER_2)).when(repository).findAllByOrderByCreatedDesc();

        List<Filter> result = service.findAll();

        assertThat(result).containsExactly(MOCK_FILTER_1, MOCK_FILTER_2);

        verify(repository).findAllByOrderByCreatedDesc();
    }

    @Test
    public void findAll_returns_0_filters_if_repository_returns_0_filters() {
        doReturn(List.of()).when(repository).findAllByOrderByCreatedDesc();

        List<Filter> result = service.findAll();

        assertThat(result).isEmpty();

        verify(repository).findAllByOrderByCreatedDesc();
    }

    @Test
    public void create_throws_ValidationException_when_criteria_is_empty() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");

        Throwable result = catchThrowable(() -> service.create(data));

        assertThat(result).isInstanceOf(ValidationException.class)
            .hasMessage("No criteria provided");

        verify(repository, never()).saveAndFlush(any());
    }

    @Test
    public void create_throws_ValidationException_when_criteria_contains_invalid_condition_for_type() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");
        data.setCriteria(
            List.of(
                new FilterCriteriaDTO(
                    CriteriaType.AMOUNT,
                    CriteriaCondition.FROM,
                    "01/01/2024"
                )
            )
        );

        Throwable result = catchThrowable(() -> service.create(data));

        assertThat(result).isInstanceOf(ValidationException.class)
            .hasMessage(
                "Criteria condition '%s' cannot be used with criteria type '%s'"
                    .formatted(CriteriaCondition.FROM, CriteriaType.AMOUNT)
            );

        verify(repository, never()).saveAndFlush(any());
    }

    @Test
    public void create_throws_ValidationException_when_criteria_contains_invalid_date() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");
        data.setCriteria(
            List.of(
                new FilterCriteriaDTO(
                    CriteriaType.DATE,
                    CriteriaCondition.FROM,
                    "1st of april"
                )
            )
        );

        Throwable result = catchThrowable(() -> service.create(data));

        assertThat(result).isInstanceOf(ValidationException.class)
            .hasMessage("Invalid date format '1st of april'");

        verify(repository, never()).saveAndFlush(any());
    }

    @Test
    public void create_throws_ValidationException_when_criteria_contains_non_numeric_amount() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");
        data.setCriteria(
            List.of(
                new FilterCriteriaDTO(
                    CriteriaType.AMOUNT,
                    CriteriaCondition.EQUALS,
                    "abc"
                )
            )
        );

        Throwable result = catchThrowable(() -> service.create(data));

        assertThat(result).isInstanceOf(ValidationException.class)
            .hasMessage("Invalid amount 'abc'. Criteria value must be a valid number");

        verify(repository, never()).saveAndFlush(any());
    }

    @Test
    public void create_saves_and_returns_flushed_filter_if_no_errors() {
        FilterDTO data = new FilterDTO();
        data.setName("New Filter");
        data.setCriteria(
            List.of(
                new FilterCriteriaDTO(
                    CriteriaType.AMOUNT,
                    CriteriaCondition.EQUALS,
                    "30"
                )
            )
        );

        doReturn(MOCK_FILTER_1).when(repository).saveAndFlush(any());

        Filter result = service.create(data);

        assertThat(result).isEqualTo(MOCK_FILTER_1);

        ArgumentCaptor<Filter> filterCaptor = ArgumentCaptor.forClass(Filter.class);
        verify(repository).saveAndFlush(filterCaptor.capture());

        Filter capturedFilter = filterCaptor.getValue();
        assertThat(capturedFilter.getName()).isEqualTo("New Filter");
        assertThat(capturedFilter.getCriteria()).hasSize(1);
        FilterCriteria capturedCriteria = capturedFilter.getCriteria().stream().toList().get(0);
        assertThat(capturedCriteria.getCriteriaType()).isEqualTo(CriteriaType.AMOUNT);
        assertThat(capturedCriteria.getCriteriaCondition()).isEqualTo(CriteriaCondition.EQUALS);
        assertThat(capturedCriteria.getValue()).isEqualTo("30");
    }
}
