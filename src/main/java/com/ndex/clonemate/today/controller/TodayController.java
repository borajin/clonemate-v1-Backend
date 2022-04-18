package com.ndex.clonemate.today.controller;

import com.ndex.clonemate.today.controller.dto.TodayTodoListResponseDto;
import com.ndex.clonemate.today.service.TodayService;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("today")
@RestController
public class TodayController {
    private final TodayService commonService;

    @GetMapping("/todolist")
    public ResponseEntity<?> getTodayTodoList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            List<TodayTodoListResponseDto> data = commonService.getTodayTodoList(1L, date);
            ApiResult<List<TodayTodoListResponseDto>> response = ApiResult.<List<TodayTodoListResponseDto>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
