package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.web.dto.TodoOverviewResponseDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateOrderAndGoalRequestDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateWithoutOrderAndGoalRequestDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateOrDeleteRequestDto;

import java.time.YearMonth;
import java.util.List;

public interface TodoRepositoryCustom {

    void updateTodos(Long userId, TodoUpdateOrDeleteRequestDto condition, TodoUpdateWithoutOrderAndGoalRequestDto params);

    void deleteTodos(Long userId, TodoUpdateOrDeleteRequestDto condition);

    List<TodoOverviewResponseDto> findTodoOverview(Long userId, YearMonth dateYm);

    void updateOrderOrGoal(Long userId, List<TodoUpdateOrderAndGoalRequestDto> params);
}
