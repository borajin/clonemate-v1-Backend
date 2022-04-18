package com.ndex.clonemate.goal.domain;

import com.ndex.clonemate.goal.domain.repository.PrivacyType;
import com.ndex.clonemate.goal.web.dto.GoalUpdateRequestDto;
import com.ndex.clonemate.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor  //update, delete 에 필요
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_no", nullable = false)
    private Long orderNo;

    @Column(nullable = false)
    private String title;

    //목표 공개 범위 전체공개(PUB), 일부공개(FOL), 나만보기(PRI), 숨기기(HID)
    @Enumerated(EnumType.STRING)    //EnumType.STRING 을 하면 ENUM NAME 그대로 저장됨 (안붙이면 정수형 숫자가 저장됨)
    @Column(length = 500, nullable = false)
    private PrivacyType privacy;

    // #(1) + rgb(6) 색상코드
    @Column(name = "title_color", length = 7, nullable = false)
    private String titleColor;

    @Builder
    public Goal(User user, Long orderNo, String title, String privacy, String titleColor) {
        this.user = user;
        this.orderNo = orderNo;
        this.title = title;
        this.privacy = PrivacyType.of(privacy);
        this.titleColor = titleColor;
    }

    //COMPLETE : 부분 업데이트 이렇게 Null 검사하는 게 효율적인지 알아보기.. -> 연로그님 답변으론 ㄱㅊ다 했음.
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
