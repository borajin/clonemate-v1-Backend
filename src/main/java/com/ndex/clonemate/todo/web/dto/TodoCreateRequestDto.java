package com.ndex.clonemate.todo.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.todo.domain.Todo;
import com.ndex.clonemate.user.domain.User;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class TodoCreateRequestDto {

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
