package com.ndex.clonemate.domain.todos.web.request;

import com.ndex.clonemate.domain.todos.model.TFCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class TodoUpdateOrDeleteRequest {
    private LocalDate date;
    private TFCode isChecked;
}
