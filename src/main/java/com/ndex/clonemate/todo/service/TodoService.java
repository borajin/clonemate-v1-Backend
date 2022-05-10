package com.ndex.clonemate.todo.service;

import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.web.dto.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


public interface TodoService {
    TodoResponseMapping getTodo(Long id);

    List<TodoWithGoalsResponseDto> getTodosWithGoals(Long userId, LocalDate date);

    List<TodoOverviewResponseDto> getTodosOverview(Long userId, YearMonth dateYm);

    void createTodo(Long userId, TodoCreateRequestDto params);

    void updateTodo(Long sessionUserId, Long id, TodoUpdateWithoutOrderAndGoalRequestDto params);

    void updateTodos(Long userId, TodoUpdateOrDeleteRequestDto condition, TodoUpdateWithoutOrderAndGoalRequestDto params);

    void updateTodosOrder(Long userId, List<TodoUpdateOrderAndGoalRequestDto> params);

    void deleteTodo(Long sessionUserId, Long id);

    void deleteTodos(Long userId, TodoUpdateOrDeleteRequestDto condition);

}