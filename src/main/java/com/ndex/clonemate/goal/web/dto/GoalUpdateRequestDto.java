package com.ndex.clonemate.goal.web.dto;

import com.ndex.clonemate.utils.CommonUtils;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class GoalUpdateRequestDto {

    private String contents;
    private String privacy;

    @Pattern(regexp = CommonUtils.REGEXP_COLOR_CODE)
    private String color;
}