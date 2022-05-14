package com.ndex.clonemate.domain.todos.web.request;

import lombok.Getter;

@Getter
public class TodoUpdateOrderAndGoalRequest {
    private Long id;
    private Long goalId;
    private Integer orderNo;
}
