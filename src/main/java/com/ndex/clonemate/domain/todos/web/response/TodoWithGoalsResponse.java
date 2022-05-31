package com.ndex.clonemate.domain.todos.web.response;

import com.ndex.clonemate.domain.todos.web.request.TodoResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodoWithGoalsResponse {
    Long id;
    Integer orderNo;
    String contents;
    String privacy;
    String color;
    List<TodoResponse> todos;
}
