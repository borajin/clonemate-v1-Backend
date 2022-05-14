package com.ndex.clonemate.domain.goals.service.Impl;

import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.goals.repository.GoalRepository;
import com.ndex.clonemate.domain.goals.repository.GoalResponseMapping;
import com.ndex.clonemate.domain.goals.service.GoalService;
import com.ndex.clonemate.domain.goals.web.request.GoalCreateRequest;
import com.ndex.clonemate.domain.goals.web.request.GoalUpdateRequest;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {

    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";
    private final static String ERROR_NO_GOAL = "[ERROR] 해당 목표가 없습니다.";

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Override
    public <T> T getGoal(Class<T> type, Long id) {
        return goalRepository.findById(type, id)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_GOAL));
    }

    @Override
    public List<GoalResponseMapping> getGoals(Long userId) {
        return goalRepository.findByUser_IdOrderByOrderNoAsc(GoalResponseMapping.class, userId);
    }

    @Override
    @Transactional
    public void createGoal(Long userId, GoalCreateRequest params) {
        User user = userRepository.findById(User.class, userId)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));

        Goal newGoal = params.toEntity(user);

        goalRepository.save(newGoal);
    }

    @Override
    @Transactional
    public void updateGoal(Long userId, Long id, GoalUpdateRequest params) {
        Goal updateGoal = goalRepository.findByUser_IdAndId(Goal.class, userId, id)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_GOAL));

        updateGoal.update(params);
    }

    @Override
    @Transactional
    public void deleteGoal(Long userId, Long id) {
        goalRepository.deleteByUser_IdAndId(userId, id);
    }
}