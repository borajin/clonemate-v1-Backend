package com.ndex.clonemate.todo.web.dto;

import lombok.Getter;

@Getter
public class TodoUpdateOrderAndGoalRequestDto {
    private Long id;
    private Long goalId;
    private Integer orderNo;
}
