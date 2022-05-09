package com.ndex.clonemate.todo.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Builder
public class TodoUpdateRequestDto {
    private Long goalId;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRepeatDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRepeatDate;

    @Pattern(regexp = "[YN]")
    private Character repeatMonYn;

    @Pattern(regexp = "[YN]")
    private Character repeatTueYn;

    @Pattern(regexp = "[YN]")
    private Character repeatWenYn;

    @Pattern(regexp = "[YN]")
    private Character repeatThuYn;

    @Pattern(regexp = "[YN]")
    private Character repeatFriYn;

    @Pattern(regexp = "[YN]")
    private Character repeatSatYn;

    @Pattern(regexp = "[YN]")
    private Character repeatSunYn;

    @Pattern(regexp = "[YN]")
    private Character checkYn;
}
