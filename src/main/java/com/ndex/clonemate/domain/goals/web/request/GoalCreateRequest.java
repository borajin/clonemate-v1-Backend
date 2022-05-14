package com.ndex.clonemate.domain.goals.web.request;

import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.goals.model.PrivacyType;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.global.utils.CommonUtils;
import java.util.Locale;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class GoalCreateRequest {

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