package com.ndex.clonemate.domain.todos.repository;

import com.ndex.clonemate.domain.todos.web.request.TodoResponse;
import com.ndex.clonemate.domain.todos.web.response.TodoOverviewResponse;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateWithoutOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrDeleteRequest;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface TodoRepositoryCustom {

    List<TodoResponse> findTodos(Long userId, LocalDate date);

    List<TodoOverviewResponse> findTodoOverview(Long userId, YearMonth dateYm);

    void updateTodos(Long userId, TodoUpdateOrDeleteRequest condition, TodoUpdateWithoutOrderAndGoalRequest params);

    void deleteTodos(Long userId, TodoUpdateOrDeleteRequest condition);

    void updateOrderOrGoal(Long userId, List<TodoUpdateOrderAndGoalRequest> params);
}
