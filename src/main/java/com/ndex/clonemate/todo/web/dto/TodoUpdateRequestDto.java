package com.ndex.clonemate.todo.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatMonYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatTueYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatWenYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatThuYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatFriYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatSatYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String repeatSunYn;

    @Size(max = 1)
    @Pattern(regexp = "[YX]")
    private String checkYn;
}
