package com.ndex.clonemate.todo.web.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateTodoOrderRequestDto {
    Long id;
    Long orderNo;
    Long goalId;
}
