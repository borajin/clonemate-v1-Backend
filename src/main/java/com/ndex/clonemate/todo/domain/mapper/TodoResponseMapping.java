package com.ndex.clonemate.todo.domain.mapper;

import com.ndex.clonemate.like.domain.LikeResponseMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public interface TodoResponseMapping {

    Long getGoalId();

    Long getId();

    Integer getOrderNo();

    String getContents();

    LocalDate getDate();

    LocalDate getStartRepeatDate();

    LocalDate getEndRepeatDate();

    HashMap<String, Boolean> getRepeatDays();

    Boolean getIsChecked();

    Set<LikeResponseMapping> getLikes();
}