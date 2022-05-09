package com.ndex.clonemate.goal.domain;

import com.ndex.clonemate.goal.domain.repository.PrivacyType;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;
import com.ndex.clonemate.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //update, delete 에 필요
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private Long orderNo;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)    //EnumType.STRING 을 하면 ENUM NAME 그대로 저장됨 (안붙이면 정수형 숫자가 저장됨)
    @Column(length = 8, nullable = false)
    private PrivacyType privacy;

    // #(1) + rgb(6) 색상코드
    @Column(length = 7, nullable = false)
    private String titleColor;

    @Builder
    public Goal(User user, Long orderNo, String title, String privacy, String titleColor) {
        this.user = user;
        this.orderNo = orderNo;
        this.title = title;
        this.privacy = PrivacyType.of(privacy);
        this.titleColor = titleColor;
    }

    public void update(GoalUpdateRequestDto params) {
        if (params.getTitle() != null) this.title = params.getTitle();
        if (params.getPrivacy() != null) this.privacy = PrivacyType.of(params.getPrivacy());
        if (params.getTitleColor() != null) this.titleColor = params.getTitleColor();
    }

    public void updateOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getPrivacy() {
        return this.privacy.getFullName();
    }
}
