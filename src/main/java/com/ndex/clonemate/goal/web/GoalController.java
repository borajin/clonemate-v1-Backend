package com.ndex.clonemate.goal.web;

import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.goal.web.dto.GoalCreateRequestDto;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/goals")
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
        @Valid @RequestBody GoalCreateRequestDto params) {
        Long sessionUserId = token.getId();
        goalService.createGoal(sessionUserId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateGoal(CustomAuthenticationToken token,@PathVariable("id") Long id,
        @Valid @RequestBody GoalUpdateRequestDto params) {
        Long sessionUserId = token.getId();
        goalService.updateGoal(sessionUserId, id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteGoal(CustomAuthenticationToken token,@PathVariable("id") Long id) {
        Long sessionUserId = token.getId();
        goalService.deleteGoal(sessionUserId, id);
        return ApiUtils.createSuccessEmptyApi();
    }
}