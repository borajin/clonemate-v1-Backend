package com.ndex.clonemate.domain.goals.model;

import com.ndex.clonemate.domain.goals.web.request.GoalUpdateRequest;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.global.utils.CommonUtils;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
    private Integer orderNo;

    @Column(nullable = false)
    private String contents;

    @Column(length = 8, nullable = false)
    private PrivacyType privacy;

    @Column(length = 7, nullable = false)   // #(1) + rgb(6) 색상코드
    private String color;

    @Builder
    public Goal(User user, Integer orderNo, String contents, PrivacyType privacy, String color) {
        this.user = user;
        this.orderNo = orderNo;
        this.contents = contents;
        this.privacy = privacy;
        this.color = color;
    }

    public String getPrivacy() {
        return this.privacy.getValue();
    }

    public void update(GoalUpdateRequest params) {
        if (!CommonUtils.isEmpty(params.getContents())) {
            this.contents = params.getContents();
        }
        if (!CommonUtils.isEmpty(params.getPrivacy())) {
            this.privacy = PrivacyType.valueOf(params.getPrivacy().toUpperCase(Locale.ROOT));
        }
        if (!CommonUtils.isEmpty(params.getColor())) {
            this.color = params.getColor();
        }
    }

    public void updateOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
