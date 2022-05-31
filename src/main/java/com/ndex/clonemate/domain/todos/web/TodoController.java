package com.ndex.clonemate.domain.todos.web;

import com.ndex.clonemate.domain.todos.model.TFCode;
import com.ndex.clonemate.domain.todos.service.TodoService;
import com.ndex.clonemate.domain.todos.web.request.TodoCreateRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrDeleteRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateOrderAndGoalRequest;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateWithoutOrderAndGoalRequest;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.global.dto.ApiResult;
import com.ndex.clonemate.global.utils.ApiUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@RequiredArgsConstructor
@RequestMapping("api/v1/todos")
@RestController
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ApiResult<?> getTodos(@RequestParam(required = false) Long userId,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ApiUtils.createSuccessApi(todoService.getTodos(userId, date));
    }

    @GetMapping("/{id}")
    public ApiResult<?> getTodo(@PathVariable("id") Long id) {
        return ApiUtils.createSuccessApi(todoService.getTodo(id));
    }

    @GetMapping("/overview")
    public ApiResult<?> getTodoOverview(@RequestParam(required = false) Long userId,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth dateYm) {
        return ApiUtils.createSuccessApi(todoService.getTodosOverview(userId, dateYm));
    }

    @PostMapping
    public ApiResult<?> createTodo(CustomAuthenticationToken token,
        @RequestBody TodoCreateRequest params) {
        Long sessionUserId = token.getId();
        todoService.createTodo(sessionUserId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateTodo(CustomAuthenticationToken token, @PathVariable("id") Long id,
        @RequestBody TodoUpdateWithoutOrderAndGoalRequest params) {
        Long sessionUserId = token.getId();
        todoService.updateTodo(sessionUserId, id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping
    public ApiResult<?> updateTodos(CustomAuthenticationToken token,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam(required = false) Boolean isChecked,
        @RequestBody TodoUpdateWithoutOrderAndGoalRequest params) {
        Long sessionUserId = token.getId();
        TodoUpdateOrDeleteRequest condition = TodoUpdateOrDeleteRequest.builder().date(date)
            .isChecked(TFCode.boolValueOf(isChecked)).build();
        todoService.updateTodos(sessionUserId, condition, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/order")
    public ApiResult<?> updateTodosOrder(CustomAuthenticationToken token,
        @RequestBody List<TodoUpdateOrderAndGoalRequest> params) {
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
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam(required = false) Boolean isChecked) {
        Long sessionUserId = token.getId();
        TodoUpdateOrDeleteRequest condition = TodoUpdateOrDeleteRequest.builder().date(date)
            .isChecked(TFCode.boolValueOf(isChecked)).build();
        todoService.deleteTodos(sessionUserId, condition);
        return ApiUtils.createSuccessEmptyApi();
    }
}