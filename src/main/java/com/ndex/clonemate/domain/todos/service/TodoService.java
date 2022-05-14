package com.ndex.clonemate.domain.todos.service;

import com.ndex.clonemate.domain.todos.repository.TodoResponseMapping;
import com.ndex.clonemate.domain.todos.web.request.TodoCreateRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrDeleteRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateWithoutOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.response.TodoOverviewResponse;
import com.ndex.clonemate.domain.todos.web.response.TodoWithGoalsResponse;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


public interface TodoService {
    TodoResponseMapping getTodo(Long id);

    List<TodoWithGoalsResponse> getTodos(Long userId, LocalDate date);

    List<TodoOverviewResponse> getTodosOverview(Long userId, YearMonth dateYm);

    void createTodo(Long userId, TodoCreateRequest params);

    void updateTodo(Long sessionUserId, Long id, TodoUpdateWithoutOrderAndGoalRequest params);

    void updateTodos(Long userId, TodoUpdateOrDeleteRequest condition, TodoUpdateWithoutOrderAndGoalRequest params);

    void updateTodosOrder(Long userId, List<TodoUpdateOrderAndGoalRequest> params);

    void deleteTodo(Long sessionUserId, Long id);

    void deleteTodos(Long userId, TodoUpdateOrDeleteRequest condition);

}