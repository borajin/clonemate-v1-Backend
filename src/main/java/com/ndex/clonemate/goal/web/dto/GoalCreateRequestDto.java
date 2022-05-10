package com.ndex.clonemate.goal.web.dto;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.goal.domain.PrivacyType;
import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.utils.CommonUtils;
import java.util.Locale;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class GoalCreateRequestDto {

    private Integer orderNo;

    @NotBlank
    private String contents;

    @NotBlank
    private String privacy;

    @NotBlank
    @Pattern(regexp = CommonUtils.REGEXP_COLOR_CODE)
    private String color;

    public Goal toEntity(User user) {
        return Goal.builder()
            .user(user)
            .orderNo(orderNo)
            .contents(contents)
            .privacy(PrivacyType.valueOf(privacy.toUpperCase(Locale.ROOT)))
            .color(color)
            .build();
    }
}