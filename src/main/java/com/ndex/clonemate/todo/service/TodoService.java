package com.ndex.clonemate.todo.service;

import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.web.dto.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


public interface TodoService {
    TodoResponseMapping getTodo(Long id);

    List<TodayTodoListResponseDto> getTodos(Long userId, LocalDate date);

    List<TodoOverviewResponseDto> getTodosOverview(Long userId, YearMonth dateYm);

    void createTodo(Long userId, TodoCreateRequestDto params);

    void updateTodo(Long id, TodoUpdateRequestDto params);

    void updateTodos(Long userId, TodosCondition condition, TodoUpdateRequestDto params);

    void deleteTodo(Long id);

    void deleteTodos(Long userId, TodosCondition condition);

}