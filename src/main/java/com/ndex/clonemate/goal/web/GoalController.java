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

    @GetMapping("/{id}")
    public ApiResult<?> getGoal(@PathVariable("id") Long id) {
        return ApiUtils.createSuccessApi(goalService.getGoal(GoalResponseMapping.class, id));
    }

    @GetMapping
    public ApiResult<?> getGoalsAndTodos() {
        return ApiUtils.createSuccessApi(goalService.getGoals(1L));
    }

    @PostMapping
    public ApiResult<?> createGoal(CustomAuthenticationToken token, @Valid @RequestBody GoalCreateRequestDto params) {
        Long userId = token.getId();
        goalService.createGoal(userId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateGoal(@PathVariable("id") Long id, @Valid @RequestBody GoalUpdateRequestDto params) {
        goalService.updateGoal(id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteGoal(@PathVariable("id") Long id) {
        goalService.deleteGoal(id);
        return ApiUtils.createSuccessEmptyApi();
    }
}