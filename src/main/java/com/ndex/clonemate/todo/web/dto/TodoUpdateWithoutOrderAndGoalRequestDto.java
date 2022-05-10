package com.ndex.clonemate.todo.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoUpdateWithoutOrderAndGoalRequestDto {
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
}
