package com.ndex.clonemate.todo.web;

import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.todo.domain.TFCode;
import com.ndex.clonemate.todo.service.TodoService;
import com.ndex.clonemate.todo.web.dto.*;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ApiResult<?> getTodosWithGoals(@RequestParam Long userId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ApiUtils.createSuccessApi(todoService.getTodosWithGoals(userId, date));
    }

    @GetMapping("/{id}")
    public ApiResult<?> getTodo(@PathVariable("id") Long id) {
        return ApiUtils.createSuccessApi(todoService.getTodo(id));
    }

    @GetMapping("/overview")
    public ApiResult<?> getTodoOverview(@RequestParam Long userId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth dateYm) {
        return ApiUtils.createSuccessApi(todoService.getTodosOverview(userId, dateYm));
    }

    @PostMapping
    public ApiResult<?> createTodo(CustomAuthenticationToken token,
        @RequestBody TodoCreateRequestDto params) {
        Long sessionUserId = token.getId();
        todoService.createTodo(sessionUserId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateTodo(CustomAuthenticationToken token, @PathVariable("id") Long id,
        @RequestBody TodoUpdateWithoutOrderAndGoalRequestDto params) {
        Long sessionUserId = token.getId();
        todoService.updateTodo(sessionUserId, id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping
    public ApiResult<?> updateTodos(CustomAuthenticationToken token,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam Boolean isChecked, @RequestBody TodoUpdateWithoutOrderAndGoalRequestDto params) {
        Long sessionUserId = token.getId();
        TodoUpdateOrDeleteRequestDto condition = TodoUpdateOrDeleteRequestDto.builder().date(date)
            .isChecked(TFCode.boolValueOf(isChecked)).build();
        todoService.updateTodos(sessionUserId, condition, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/order")
    public ApiResult<?> updateTodosOrder(CustomAuthenticationToken token, @RequestBody List<TodoUpdateOrderAndGoalRequestDto> params) {
        Long sessionUserId = token.getId();
        todoService.updateTodosOrder(sessionUserId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteTodo(CustomAuthenticationToken token, @PathVariable("id") Long id) {
        Long sessionUserId = token.getId();
        todoService.deleteTodo(sessionUserId, id);
        return ApiUtils.createSuccessEmptyApi();

    }

    @DeleteMapping
    public ApiResult<?> deleteTodos(CustomAuthenticationToken token,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam Boolean isChecked) {
        Long sessionUserId = token.getId();
        TodoUpdateOrDeleteRequestDto condition = TodoUpdateOrDeleteRequestDto.builder().date(date)
            .isChecked(TFCode.boolValueOf(isChecked)).build();
        todoService.deleteTodos(sessionUserId, condition);
        return ApiUtils.createSuccessEmptyApi();
    }
}