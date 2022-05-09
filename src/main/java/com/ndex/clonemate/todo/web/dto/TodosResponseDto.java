package com.ndex.clonemate.todo.web.dto;

import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodosResponseDto {
    Long id;
    Long orderNo;
    String title;
    String privacy;
    String titleColor;
    List<TodoResponseMapping> todos;
}
