package com.ndex.clonemate.domain.todos.web.response;

import com.ndex.clonemate.domain.todos.repository.TodoResponseMapping;
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
    List<TodoResponseMapping> todos;
}
