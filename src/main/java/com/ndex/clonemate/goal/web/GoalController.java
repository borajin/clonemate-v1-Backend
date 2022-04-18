package com.ndex.clonemate.goal.web;

import com.ndex.clonemate.goal.domain.mapper.GoalResponseMapping;
import com.ndex.clonemate.goal.service.GoalService;
import com.ndex.clonemate.goal.web.dto.GoalCreateRequestDto;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;
import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/goals")
@RestController
public class GoalController {
    private final GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGoal(@PathVariable("id") Long id) {
        try {
            GoalResponseMapping data = goalService.getGoal(GoalResponseMapping.class, id);
            ApiResult<GoalResponseMapping> response = ApiResult.<GoalResponseMapping>builder().success(true).data(data).build();

            System.out.println("h23h23h23h");

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getGoalsAndTodos() {
        try {
            List<GoalResponseMapping> data = goalService.getGoals(1L);
            ApiResult<List<GoalResponseMapping>> response = ApiResult.<List<GoalResponseMapping>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<List<TodoResponseMapping>> response = ApiResult.<List<TodoResponseMapping>>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createGoal(@Valid @RequestBody GoalCreateRequestDto params) {
        try {
            goalService.createGoal(1L, params);

            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable("id") Long id, @Valid @RequestBody GoalUpdateRequestDto params) {
        try {
            goalService.updateGoal(id, params);

            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable("id") Long id) {
        try {
            goalService.deleteGoal(id);

            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}