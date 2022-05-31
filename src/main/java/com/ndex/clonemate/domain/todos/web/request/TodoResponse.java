package com.ndex.clonemate.domain.todos.web.request;

import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.likes.repository.LikeResponseMapping;
import com.ndex.clonemate.domain.todos.model.TFCode;
import com.ndex.clonemate.global.utils.CommonUtils.Days;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import lombok.Getter;

@Getter
public class TodoResponse {
    Long id;
    Long goalId;
    Integer orderNo;
    String contents;
    LocalDate date;
    LocalDate startRepeatDate;
    LocalDate endRepeatDate;
    HashMap<String, Boolean> repeatDays;
    Boolean isChecked;
    Set<LikeResponseMapping> likes;

    public TodoResponse(Long id, Goal goal, Integer orderNo, String contents, LocalDate date,
        LocalDate startRepeatDate, LocalDate endRepeatDate, TFCode isRepeatMon,
        TFCode isRepeatTue, TFCode isRepeatWen, TFCode isRepeatThu, TFCode isRepeatFri,
        TFCode isRepeatSat, TFCode isRepeatSun, TFCode isChecked) {
        this.id = id;
        this.goalId = goal.getId();
        this.orderNo = orderNo;
        this.contents = contents;
        this.date = date;
        this.startRepeatDate = startRepeatDate;
        this.endRepeatDate = endRepeatDate;
        this.repeatDays = this.getRepeatDays(isRepeatMon, isRepeatTue, isRepeatWen, isRepeatThu,
            isRepeatFri, isRepeatSat, isRepeatSun);
        this.isChecked = isChecked.isBoolValue();
    }

    private HashMap<String, Boolean> getRepeatDays(TFCode isRepeatMon,
        TFCode isRepeatTue, TFCode isRepeatWen, TFCode isRepeatThu, TFCode isRepeatFri,
        TFCode isRepeatSat, TFCode isRepeatSun) {
        HashMap<String, Boolean> repeatDays = new HashMap<>();

        repeatDays.put(Days.MON.name(), isRepeatMon.isBoolValue());
        repeatDays.put(Days.TUE.name(), isRepeatTue.isBoolValue());
        repeatDays.put(Days.WED.name(), isRepeatWen.isBoolValue());
        repeatDays.put(Days.THU.name(), isRepeatThu.isBoolValue());
        repeatDays.put(Days.FRI.name(), isRepeatFri.isBoolValue());
        repeatDays.put(Days.SAT.name(), isRepeatSat.isBoolValue());
        repeatDays.put(Days.SUN.name(), isRepeatSun.isBoolValue());

        return repeatDays;
    }
}
