package com.ndex.clonemate.todo.web.dto;

import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodoWithGoalsResponseDto {
    Long id;
    Integer orderNo;
    String contents;
    String privacy;
    String color;
    List<TodoResponseMapping> todos;
}
