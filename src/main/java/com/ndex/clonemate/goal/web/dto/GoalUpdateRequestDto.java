package com.ndex.clonemate.goal.web.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Builder
@Getter
public class GoalUpdateRequestDto {
    private String title;
    private String privacy;

    @Pattern(regexp = "^#(?:[0-9a-f]{2}){3}$")
    private String titleColor;
}