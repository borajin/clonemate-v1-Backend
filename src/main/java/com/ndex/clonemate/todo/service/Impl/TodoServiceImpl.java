package com.ndex.clonemate.todo.service.Impl;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.todo.domain.Todo;
import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.domain.repository.TodoRepository;
import com.ndex.clonemate.todo.service.TodoService;
import com.ndex.clonemate.todo.web.dto.*;
import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
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
        return todoRepository.findById(TodoResponseMapping.class, id).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));
    }

    @Override
    public List<TodoResponseMapping> getTodos(Long userId, LocalDate date) {
        return todoRepository.findByUser_IdAndStartRepeatDateLessThanEqualAndEndRepeatDateGreaterThanEqualOrderByCheckYnAscOrderNoAsc(TodoResponseMapping.class, userId, date, date);
    }

    @Override
    public List<TodoOverviewResponseDto> getTodosOverview(Long userId, YearMonth dateYm) {
        return todoRepository.findTodoOverview(userId, dateYm);
    }

    @Override
    @Transactional
    public void createTodo(Long userId, TodoCreateRequestDto params) {
        //TODO : user 를 select 할 필요 없이, security 에서 로그인 한 유저의 userId 정보를 얻어오는 것처럼 User class instance 를 넘겨주면 되지 않을까?
        User user = userRepository.findById(User.class, userId).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
        Goal goal = goalService.getGoal(Goal.class, params.getGoalId());

        Todo newTodo = params.toEntity(user, goal);

        todoRepository.save(newTodo);
    }

    @Override
    @Transactional
    public void updateTodo(Long id, TodoUpdateRequestDto params) {
        Todo updateTodo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));

        updateTodo.update(params);
    }

    @Override
    @Transactional
    public void updateTodos(Long userId, TodosCondition condition, TodoUpdateRequestDto params) {
        todoRepository.updateTodos(userId, condition, params);
    }

    @Override
    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteTodos(Long userId, TodosCondition condition) {
        todoRepository.deleteTodos(userId, condition);
    }
}