package com.ndex.clonemate.domain.goals.web.request;

import com.ndex.clonemate.global.utils.CommonUtils;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class GoalUpdateRequest {

    private String contents;
    private String privacy;

    @Pattern(regexp = CommonUtils.REGEXP_COLOR_CODE)
    private String color;
}