package com.ndex.clonemate.todo.web;

import com.ndex.clonemate.todo.domain.mapper.TodoResponseMapping;
import com.ndex.clonemate.todo.service.TodoService;
import com.ndex.clonemate.todo.web.dto.*;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") Long id) {
        try {
            TodoResponseMapping data = todoService.getTodo(id);
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getTodos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            List<TodoResponseMapping> data = todoService.getTodos(1L, date);
            ApiResult<List<TodoResponseMapping>> response = ApiResult.<List<TodoResponseMapping>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<List<TodoResponseMapping>> response = ApiResult.<List<TodoResponseMapping>>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/overview")
    public ResponseEntity<?> getTodoOverview(@RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth dateYm) {
        try {
            List<TodoOverviewResponseDto> data = todoService.getTodosOverview(1L, dateYm);

            ApiResult<List<TodoOverviewResponseDto>> response = ApiResult.<List<TodoOverviewResponseDto>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<List<TodoOverviewResponseDto>> response = ApiResult.<List<TodoOverviewResponseDto>>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoCreateRequestDto params) {
        try {
            todoService.createTodo(1L, params);

            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoUpdateRequestDto params) {
        try {
            todoService.updateTodo(id, params);

            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateTodos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam String checkYn, @RequestBody TodoUpdateRequestDto params) {
        try {
            TodosCondition condition = TodosCondition.builder().date(date).checkYn(checkYn).build();
            todoService.updateTodos(1L, condition, params);

            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        try {
            todoService.deleteTodo(id);

            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //특정 날짜 미완료 or 전체 투두 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteTodos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam String checkYn) {
        try {
            TodosCondition condition = TodosCondition.builder().date(date).checkYn(checkYn).build();
            todoService.deleteTodos(1L, condition);

            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<TodoResponseMapping> response = ApiResult.<TodoResponseMapping>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}