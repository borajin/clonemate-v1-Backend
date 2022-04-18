package com.ndex.clonemate.goal.web.dto;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter //직렬화에 필요
public class GoalCreateRequestDto {
    private Long orderNo;

    @NotBlank
    private String title;

    @NotBlank
    private String privacy;

    @NotBlank
    @Pattern(regexp = "^#(?:[0-9a-f]{2}){3}$")
    private String titleColor;

    public Goal toEntity(User user) {
        return Goal.builder()
                .user(user)
                .orderNo(orderNo)
                .title(title)
                .privacy(privacy)
                .titleColor(titleColor)
                .build();
    }
}