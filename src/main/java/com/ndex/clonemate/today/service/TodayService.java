package com.ndex.clonemate.today.service;

import com.ndex.clonemate.today.controller.dto.TodayTodoListResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface TodayService {
    List<TodayTodoListResponseDto> getTodayTodoList(Long userId, LocalDate date);

    //TODO : 순서 변경 로직 생성하기
}
