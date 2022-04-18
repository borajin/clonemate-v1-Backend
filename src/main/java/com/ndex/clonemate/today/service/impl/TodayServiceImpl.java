package com.ndex.clonemate.today.service.impl;

import com.ndex.clonemate.today.controller.dto.TodayTodoListResponseDto;
import com.ndex.clonemate.today.service.TodayService;
import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TodayServiceImpl implements TodayService {
    private final GoalService goalService;
    private final TodoService todoService;

    @Override
    public List<TodayTodoListResponseDto> getTodayTodoList(Long userId, LocalDate date) {
        //goal, 투두 한꺼번에 다 가져오려 했는데... 더 복잡할 듯 해서 아래처럼 구현함.
        //goal, 투두, like, user 조회한다고 총 4번의 select 가 일어남.

        List<GoalResponseMapping> goals = goalService.getGoals(userId);
        List<TodoResponseMapping> todos = todoService.getTodos(userId, date);

        List<TodayTodoListResponseDto> todayTodoList = new ArrayList<>();

        //foreach 는 순서 보장. 새로운 object return x (java 도 그런지 모름)
        goals.forEach(goal -> {
            List<TodoResponseMapping> newTodos = new ArrayList<>();

            todos.forEach(todo -> {
                if(Objects.equals(todo.getGoalId(), goal.getId())) {
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    String dayOfWeekText = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase(Locale.ROOT);

                    System.out.println(dayOfWeekText);
                    if(todo.getRepeatDays().get(dayOfWeekText).equals("y")) {
                        newTodos.add(todo);
                    }
                }
            });

            TodayTodoListResponseDto newTodayTodo = TodayTodoListResponseDto.builder().id(goal.getId())
                    .orderNo(goal.getOrderNo())
                    .privacy(goal.getPrivacy())
                    .titleColor(goal.getTitleColor())
                    .title(goal.getTitle())
                    .todos(newTodos)
                    .build();

            todayTodoList.add(newTodayTodo);
        });

        return todayTodoList;
    }
}
