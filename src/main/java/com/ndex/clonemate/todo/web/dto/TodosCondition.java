package com.ndex.clonemate.todo.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class TodosCondition {
    private LocalDate date;
    private Character checkYn;
}
