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
        return todoRepository.findById(TodoResponseMapping.class, id).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_TODO));
    }

    @Override
    public List<TodosResponseDto> getTodos(Long userId, LocalDate date) {
        List<GoalResponseMapping> goals = goalService.getGoals(userId);
        List<TodoResponseMapping> todos = todoRepository.findByUser_IdAndDateOrderByCheckYnAscOrderNoAsc(TodoResponseMapping.class, userId, date);

        HashMap<Long, List<TodoResponseMapping>> temp = new HashMap<>();
        goals.forEach(goal -> {
            temp.put(goal.getId(), new ArrayList<>());
        });

        todos.forEach(todo -> {
            temp.get(todo.getGoalId()).add(todo);
        });

        List<TodosResponseDto> results = new ArrayList<>();
        goals.forEach(goal -> {
            results.add(TodosResponseDto.builder()
                    .id(goal.getId())
                    .title(goal.getTitle())
                    .orderNo(goal.getOrderNo())
                    .privacy(goal.getPrivacy())
                    .titleColor(goal.getTitleColor())
                    .todos(temp.get(goal.getId()))
                    .build());
        });

        return results;
    }

//    @Override
//    public List<TodayTodoListResponseDto> getTodos(Long userId, LocalDate date) {
//        //goal, 투두 한꺼번에 다 가져오려 했는데... 더 복잡할 듯 해서 아래처럼 구현함.
//        //goal, 투두, like, user 조회한다고 총 4번의 select 가 일어남.
//
//        List<GoalResponseMapping> goals = goalService.getGoals(userId);
//        List<TodoResponseMapping> todos = todoRepository.findByUser_IdAndStartRepeatDateLessThanEqualAndEndRepeatDateGreaterThanEqualOrderByCheckYnAscOrderNoAsc(TodoResponseMapping.class, userId, date, date);
//
//        List<TodayTodoListResponseDto> todayTodoList = new ArrayList<>();
//
//        //foreach 는 순서 보장. 새로운 object return x (java 도 그런지 모름)
//        goals.forEach(goal -> {
//            List<TodoResponseMapping> newTodos = new ArrayList<>();
//
//            todos.forEach(todo -> {
//                if (Objects.equals(todo.getGoalId(), goal.getId())) {
//                    DayOfWeek dayOfWeek = date.getDayOfWeek();
//                    String dayOfWeekText = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase(Locale.ROOT);
//
//                    System.out.println(dayOfWeekText);
//                    if (todo.getRepeatDays().get(dayOfWeekText).equals("Y")) {
//                        newTodos.add(todo);
//                    }
//                }
//            });
//
//            TodayTodoListResponseDto newTodayTodo = TodayTodoListResponseDto.builder().id(goal.getId())
//                    .orderNo(goal.getOrderNo())
//                    .privacy(goal.getPrivacy())
//                    .titleColor(goal.getTitleColor())
//                    .title(goal.getTitle())
//                    .todos(newTodos)
//                    .build();
//
//            todayTodoList.add(newTodayTodo);
//        });
//
//        return todayTodoList;
//    }

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