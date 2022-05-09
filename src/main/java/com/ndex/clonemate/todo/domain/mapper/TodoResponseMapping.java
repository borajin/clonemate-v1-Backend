package com.ndex.clonemate.todo.domain.mapper;

import com.ndex.clonemate.like.domain.LikeResponseMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public interface TodoResponseMapping {
    Long getGoalId();
    Long getId();
    Long getOrderNo();
    String getTitle();
    LocalDate getDate();
    LocalDate getStartRepeatDate();
    LocalDate getEndRepeatDate();
    HashMap<String, Character> getRepeatDays();
    String getCheckYn();
    Set<LikeResponseMapping> getLikes();
}