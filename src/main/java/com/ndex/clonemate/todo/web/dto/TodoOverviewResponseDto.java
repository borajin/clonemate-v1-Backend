package com.ndex.clonemate.todo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoOverviewResponseDto {
    Integer numTodoDay;
    Long numTodoCount;
    Boolean isCompleted;
}