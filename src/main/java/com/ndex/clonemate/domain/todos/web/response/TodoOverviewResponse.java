package com.ndex.clonemate.domain.todos.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoOverviewResponse {
    Integer numTodoDay;
    Long numTodoCount;
    Boolean isCompleted;
}