package com.ndex.clonemate.todo.web;

import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.todo.service.TodoService;
import com.ndex.clonemate.todo.web.dto.*;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
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
    public ApiResult<?> getTodos(@RequestParam Long userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ApiUtils.createSuccessApi(todoService.getTodos(userId, date));
    }

    @GetMapping("/{id}")
    public ApiResult<?> getTodo(@PathVariable("id") Long id) {
        return ApiUtils.createSuccessApi(todoService.getTodo(id));
    }

    @GetMapping("/overview")
    public ApiResult<?> getTodoOverview(@RequestParam Long userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth dateYm) {
        return ApiUtils.createSuccessApi(todoService.getTodosOverview(userId, dateYm));
    }

    @PostMapping
    public ApiResult<?> createTodo(CustomAuthenticationToken token, @RequestBody TodoCreateRequestDto params) {
        Long userId = token.getId();
        todoService.createTodo(userId, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/{id}")
    public ApiResult<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoUpdateRequestDto params) {
        todoService.updateTodo(id, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping
    public ApiResult<?> updateTodos(CustomAuthenticationToken token, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam Character checkYn, @RequestBody TodoUpdateRequestDto params) {
        Long userId = token.getId();
        TodosCondition condition = TodosCondition.builder().date(date).checkYn(checkYn).build();
        todoService.updateTodos(userId, condition, params);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ApiUtils.createSuccessEmptyApi();

    }

    //특정 날짜 미완료 or 전체 투두 삭제
    @DeleteMapping
    public ApiResult<?> deleteTodos(CustomAuthenticationToken token, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam Character checkYn) {
        Long userId = token.getId();
        TodosCondition condition = TodosCondition.builder().date(date).checkYn(checkYn).build();
        todoService.deleteTodos(userId, condition);
        return ApiUtils.createSuccessEmptyApi();
    }
}