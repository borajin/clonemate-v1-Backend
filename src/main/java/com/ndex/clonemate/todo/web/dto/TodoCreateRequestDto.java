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

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatMonYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatTueYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatWenYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatThuYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatFriYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatSatYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatSunYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String checkYn;

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
                .checkYn(checkYn)
                .build();
    }
}
