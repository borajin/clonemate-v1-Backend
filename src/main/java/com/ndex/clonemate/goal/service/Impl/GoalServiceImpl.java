package com.ndex.clonemate.goal.service.Impl;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.goal.domain.repository.GoalRepository;
import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.goal.web.dto.GoalCreateRequestDto;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;
import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.repository.UserRepository;
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
        return goalRepository.findById(type, id).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_GOAL));
    }

    @Override
    public List<GoalResponseMapping> getGoals(Long userId) {
        return goalRepository.findByUser_IdOrderByOrderNoAsc(GoalResponseMapping.class, userId);
    }

    @Override
    @Transactional
    public void createGoal(Long userId, GoalCreateRequestDto params) {
        //TODO : user 를 select 할 필요 없이, security 에서 로그인 한 유저의 userId 정보를 얻어오는 것처럼 User class instance 를 넘겨주면 되지 않을까?
        User user = userRepository.findById(User.class, userId).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));

        Goal newGoal = params.toEntity(user);

        goalRepository.save(newGoal);
    }

    @Override
    @Transactional
    public void updateGoal(Long id, GoalUpdateRequestDto params) {
        Goal updateGoal = goalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_GOAL));

        updateGoal.update(params);
    }

    @Override
    @Transactional
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}