package com.ndex.clonemate.goal.service;

import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.web.dto.GoalCreateRequestDto;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;

import java.util.List;

public interface GoalService {
    <T> T getGoal(Class<T> type, Long id);

    List<GoalResponseMapping> getGoals(Long userId);

    void createGoal(Long userId, GoalCreateRequestDto params);

    void updateGoal(Long id, GoalUpdateRequestDto params);

    void deleteGoal(Long id);
}
