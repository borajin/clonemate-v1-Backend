package com.ndex.clonemate.todo.web.dto;

import com.ndex.clonemate.todo.domain.TFCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class TodoUpdateOrDeleteRequestDto {
    private LocalDate date;
    private TFCode isChecked;
}
