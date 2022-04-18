package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.web.dto.TodoOverviewResponseDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateRequestDto;
import com.ndex.clonemate.todo.web.dto.TodosCondition;
import com.ndex.clonemate.todo.web.dto.UpdateTodoOrderRequestDto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface TodoRepositoryCustom {
    //TODO : update 할 때 없는 todo 의 경우 에러 띄우지 않고 query 를 날림
    void updateTodos(Long userId, TodosCondition condition, TodoUpdateRequestDto params);

    void deleteTodos(Long userId, TodosCondition condition);

    List<TodoOverviewResponseDto> findTodoOverview(Long userId, YearMonth dateYm);

    //void updateOrder(Long userId, Long goalId, List<UpdateTodoOrderRequestDto> params);
}
