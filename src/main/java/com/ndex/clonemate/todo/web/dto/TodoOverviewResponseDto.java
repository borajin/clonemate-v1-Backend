package com.ndex.clonemate.todo.web.dto;

import lombok.Getter;

@Getter
public class TodoOverviewResponseDto {
    Integer numTodoDay;
    Long numTodoCount;
    String ynComplete;

    public TodoOverviewResponseDto(Integer numTodoDay, Long numTodoCount, String ynComplete) {
        this.numTodoDay = numTodoDay;
        this.numTodoCount = numTodoCount;
        this.ynComplete = ynComplete;
    }
}