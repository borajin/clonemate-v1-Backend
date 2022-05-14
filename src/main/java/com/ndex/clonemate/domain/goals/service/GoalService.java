package com.ndex.clonemate.domain.goals.service;

import com.ndex.clonemate.domain.goals.repository.GoalResponseMapping;
import com.ndex.clonemate.domain.goals.web.request.GoalCreateRequest;
import com.ndex.clonemate.domain.goals.web.request.GoalUpdateRequest;

import java.util.List;

public interface GoalService {
    <T> T getGoal(Class<T> type, Long id);

    List<GoalResponseMapping> getGoals(Long userId);

    void createGoal(Long userId, GoalCreateRequest params);

    void updateGoal(Long userId, Long id, GoalUpdateRequest params);

    void deleteGoal(Long userId, Long id);
}
