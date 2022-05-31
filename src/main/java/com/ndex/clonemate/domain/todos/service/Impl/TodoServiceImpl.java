package com.ndex.clonemate.domain.todos.service.Impl;

import com.ndex.clonemate.domain.todos.repository.TodoRepository;
import com.ndex.clonemate.domain.todos.service.TodoService;
import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.goals.repository.GoalResponseMapping;
import com.ndex.clonemate.domain.goals.service.GoalService;
import com.ndex.clonemate.domain.todos.model.Todo;
import com.ndex.clonemate.domain.todos.repository.TodoResponseMapping;
import com.ndex.clonemate.domain.todos.web.request.TodoCreateRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoResponse;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrDeleteRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateWithoutOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.response.TodoOverviewResponse;
import com.ndex.clonemate.domain.todos.web.response.TodoWithGoalsResponse;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";
    private final static String ERROR_NO_TODO = "[ERROR] 해당 투두가 없습니다.";

    private final TodoRepository todoRepository;
    private final GoalService goalService;
    private final UserRepository userRepository;

    @Override
    public TodoResponseMapping getTodo(Long id) {
        return todoRepository.findById(TodoResponseMapping.class, id)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));
    }

    @Override
    public List<TodoWithGoalsResponse> getTodos(Long userId, LocalDate date) {
        List<GoalResponseMapping> goals = goalService.getGoals(userId);
        List<TodoResponse> todos = todoRepository.findTodos(userId, date);

        HashMap<Long, List<TodoResponse>> temp = new HashMap<>();
        goals.forEach(goal -> {
            temp.put(goal.getId(), new ArrayList<>());
        });

        todos.forEach(todo -> {
            temp.get(todo.getGoalId()).add(todo);
        });

        List<TodoWithGoalsResponse> results = new ArrayList<>();
        goals.forEach(goal -> {
            results.add(TodoWithGoalsResponse.builder()
                .id(goal.getId())
                .contents(goal.getContents())
                .orderNo(goal.getOrderNo())
                .privacy(goal.getPrivacy())
                .color(goal.getColor())
                .todos(temp.get(goal.getId()))
                .build());
        });

        return results;
    }

    @Override
    public List<TodoOverviewResponse> getTodosOverview(Long userId, YearMonth dateYm) {
        return todoRepository.findTodoOverview(userId, dateYm);
    }

    @Override
    @Transactional
    public void createTodo(Long userId, TodoCreateRequest params) {
        User user = userRepository.findById(User.class, userId)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
        Goal goal = goalService.getGoal(Goal.class, params.getGoalId());

        Todo newTodo = params.toEntity(user, goal);

        todoRepository.save(newTodo);
    }

    @Override
    @Transactional
    public void updateTodo(Long userId, Long id, TodoUpdateWithoutOrderAndGoalRequest params) {
        Todo updateTodo = todoRepository.findByUser_IdAndId(Todo.class, userId, id)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));

        updateTodo.update(params);
    }

    @Override
    @Transactional
    public void updateTodos(Long userId, TodoUpdateOrDeleteRequest condition,
        TodoUpdateWithoutOrderAndGoalRequest params) {
        todoRepository.updateTodos(userId, condition, params);
    }

    @Override
    @Transactional
    public void updateTodosOrder(Long userId, List<TodoUpdateOrderAndGoalRequest> params) {
        todoRepository.updateOrderOrGoal(userId, params);
    }

    @Override
    @Transactional
    public void deleteTodo(Long userId, Long id) {
        todoRepository.deleteByUser_IdAndId(userId, id);
    }

    @Override
    @Transactional
    public void deleteTodos(Long userId, TodoUpdateOrDeleteRequest condition) {
        todoRepository.deleteTodos(userId, condition);
    }
}