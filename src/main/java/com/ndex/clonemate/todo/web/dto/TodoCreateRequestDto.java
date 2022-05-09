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
    private Long orderNo;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRepeatDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRepeatDate;

    @Pattern(regexp = "[YN]")
    private Character repeatMonYn;

    @Pattern(regexp = "[YN]")
    private Character repeatTueYn;

    @Pattern(regexp = "[YN]")
    private Character repeatWenYn;

    @Pattern(regexp = "[YN]")
    private Character repeatThuYn;

    @Pattern(regexp = "[YN]")
    private Character repeatFriYn;

    @Pattern(regexp = "[YN]")
    private Character repeatSatYn;

    @Pattern(regexp = "[YN]")
    private Character repeatSunYn;

    @Pattern(regexp = "[YN]")
    private Character checkYn;

    public Todo toEntity(User user, Goal goal) {
        return Todo.builder()
                .user(user)
                .goal(goal)
                .orderNo(orderNo)
                .title(title)
                .date(date)
                .startRepeatDate(startRepeatDate)
                .endRepeatDate(endRepeatDate)
                .repeatMonYn(repeatMonYn)
                .repeatTueYn(repeatTueYn)
                .repeatWenYn(repeatWenYn)
                .repeatThuYn(repeatThuYn)
                .repeatSatYn(repeatSatYn)
                .repeatFriYn(repeatFriYn)
                .repeatSatYn(repeatSatYn)
                .repeatSunYn(repeatSunYn)
                .build();
    }
}
