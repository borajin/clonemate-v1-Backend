package com.ndex.clonemate.domain.goals.web;

import com.ndex.clonemate.domain.goals.repository.GoalResponseMapping;
import com.ndex.clonemate.domain.goals.service.GoalService;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.domain.goals.web.request.GoalCreateRequest;
import com.ndex.clonemate.domain.goals.web.request.GoalUpdateRequest;
import com.ndex.clonemate.global.dto.ApiResult;
import com.ndex.clonemate.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("api/v1/goals")
@RestController
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    public ApiResult<?> getGoals(CustomAuthenticationToken token) {
        Long sessionUserId = token.getId();
        return ApiUtils.createSuccessApi(goalService.getGoals(sessionUserId));
    }

    @GetMapping("/{id}")
    public ApiResult<?> getGoal(@PathVariable("id") Long id) {
        return ApiUtils.createSuccessApi(goalService.getGoal(GoalResponseMapping.class, id));
    }

    @PostMapping
    public ApiResult<?> createGoal(CustomAuthenticationToken token,
        @Valid @RequestBody GoalCreateRequest params) {
        Long sessionUserId = token.getId();
        goalService.createGoal(sessionUserId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateGoal(CustomAuthenticationToken token, @PathVariable("id") Long id,
        @Valid @RequestBody GoalUpdateRequest params) {
        Long sessionUserId = token.getId();
        goalService.updateGoal(sessionUserId, id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteGoal(CustomAuthenticationToken token, @PathVariable("id") Long id) {
        Long sessionUserId = token.getId();
        goalService.deleteGoal(sessionUserId, id);
        return ApiUtils.createSuccessEmptyApi();
    }
}