package com.ndex.clonemate.domain.todos.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.likes.repository.LikeResponseMapping;

import com.ndex.clonemate.domain.todos.model.TFCode;
import com.ndex.clonemate.global.utils.CommonUtils.Days;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public interface TodoResponseMapping {

    Long getId();

    @JsonIgnore
    Goal getGoal();

    Integer getOrderNo();

    String getContents();

    LocalDate getDate();

    LocalDate getStartRepeatDate();

    LocalDate getEndRepeatDate();

    @JsonIgnore
    TFCode getIsRepeatMon();

    @JsonIgnore
    TFCode getIsRepeatTue();

    @JsonIgnore
    TFCode getIsRepeatWen();

    @JsonIgnore
    TFCode getIsRepeatThu();

    @JsonIgnore
    TFCode getIsRepeatFri();

    @JsonIgnore
    TFCode getIsRepeatSat();

    @JsonIgnore
    TFCode getIsRepeatSun();

    Boolean getIsChecked();

    Set<LikeResponseMapping> getLikes();

    default Long getGoalId() {
        return getGoal().getId();
    }

    default HashMap<String, Boolean> getRepeatDays() {
        HashMap<String, Boolean> repeatDays = new HashMap<>();

        repeatDays.put(Days.MON.name(), getIsRepeatMon().isBoolValue());
        repeatDays.put(Days.TUE.name(), getIsRepeatTue().isBoolValue());
        repeatDays.put(Days.WED.name(), getIsRepeatWen().isBoolValue());
        repeatDays.put(Days.THU.name(), getIsRepeatThu().isBoolValue());
        repeatDays.put(Days.FRI.name(), getIsRepeatFri().isBoolValue());
        repeatDays.put(Days.SAT.name(), getIsRepeatSat().isBoolValue());
        repeatDays.put(Days.SUN.name(), getIsRepeatSun().isBoolValue());

        return repeatDays;
    }
}