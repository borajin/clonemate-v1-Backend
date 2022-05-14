package com.ndex.clonemate.domain.todos.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ndex.clonemate.domain.todos.model.Todo;
import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.user.repository.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoCreateRequest {

    private Long goalId;
    private Integer orderNo;
    private String contents;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRepeatDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRepeatDate;

    private Boolean isRepeatMon;

    private Boolean isRepeatTue;

    private Boolean isRepeatWen;

    private Boolean isRepeatThu;

    private Boolean isRepeatFri;

    private Boolean isRepeatSat;

    private Boolean isRepeatSun;

    private Boolean isChecked;

    public Todo toEntity(User user, Goal goal) {
        return Todo.builder()
            .user(user)
            .goal(goal)
            .orderNo(orderNo)
            .contents(contents)
            .date(date)
            .startRepeatDate(startRepeatDate)
            .endRepeatDate(endRepeatDate)
            .isRepeatMon(isRepeatMon)
            .isRepeatTue(isRepeatTue)
            .isRepeatWen(isRepeatWen)
            .isRepeatThu(isRepeatThu)
            .isRepeatSat(isRepeatSat)
            .isRepeatFri(isRepeatFri)
            .isRepeatSat(isRepeatSat)
            .isRepeatSun(isRepeatSun)
            .build();
    }
}
