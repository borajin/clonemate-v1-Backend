package com.ndex.clonemate.todo.service.Impl;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.todo.domain.Todo;
import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.domain.repository.TodoRepository;
import com.ndex.clonemate.todo.service.TodoService;
import com.ndex.clonemate.todo.web.dto.*;
import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.UserRepository;
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
    public List<TodoWithGoalsResponseDto> getTodosWithGoals(Long userId, LocalDate date) {
        List<GoalResponseMapping> goals = goalService.getGoals(userId);
        List<TodoResponseMapping> todos = todoRepository.findByUser_IdAndDateOrderByIsCheckedAscOrderNoAsc(
            TodoResponseMapping.class, userId, date);

        HashMap<Long, List<TodoResponseMapping>> temp = new HashMap<>();
        goals.forEach(goal -> {
            temp.put(goal.getId(), new ArrayList<>());
        });

        todos.forEach(todo -> {
            temp.get(todo.getGoalId()).add(todo);
        });

        List<TodoWithGoalsResponseDto> results = new ArrayList<>();
        goals.forEach(goal -> {
            results.add(TodoWithGoalsResponseDto.builder()
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
    public List<TodoOverviewResponseDto> getTodosOverview(Long userId, YearMonth dateYm) {
        return todoRepository.findTodoOverview(userId, dateYm);
    }

    @Override
    @Transactional
    public void createTodo(Long userId, TodoCreateRequestDto params) {
        User user = userRepository.findById(User.class, userId)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
        Goal goal = goalService.getGoal(Goal.class, params.getGoalId());

        Todo newTodo = params.toEntity(user, goal);

        todoRepository.save(newTodo);
    }

    @Override
    @Transactional
    public void updateTodo(Long userId, Long id, TodoUpdateWithoutOrderAndGoalRequestDto params) {
        Todo updateTodo = todoRepository.findByUser_IdAndId(Todo.class, userId, id)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));

        updateTodo.update(params);
    }

    @Override
    @Transactional
    public void updateTodos(Long userId, TodoUpdateOrDeleteRequestDto condition,
        TodoUpdateWithoutOrderAndGoalRequestDto params) {
        todoRepository.updateTodos(userId, condition, params);
    }

    @Override
    @Transactional
    public void updateTodosOrder(Long userId, List<TodoUpdateOrderAndGoalRequestDto> params) {
        todoRepository.updateOrderOrGoal(userId, params);
    }

    @Override
    @Transactional
    public void deleteTodo(Long userId, Long id) {
        todoRepository.deleteByUser_IdAndId(userId, id);
    }

    @Override
    @Transactional
    public void deleteTodos(Long userId, TodoUpdateOrDeleteRequestDto condition) {
        todoRepository.deleteTodos(userId, condition);
    }
}