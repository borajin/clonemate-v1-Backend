package com.ndex.clonemate.domain.todos.model;

import com.ndex.clonemate.domain.goals.model.Goal;
import com.ndex.clonemate.domain.likes.model.Like;
import com.ndex.clonemate.domain.todos.web.request.TodoUpdateWithoutOrderAndGoalRequest;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.global.utils.CommonUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private final Set<Like> likes = new HashSet<>();

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = false)
    private String contents;

    private LocalDate date;

    private LocalDate startRepeatDate;

    private LocalDate endRepeatDate;

    @Column(nullable = false)
    private TFCode isRepeatMon;

    @Column(nullable = false)
    private TFCode isRepeatTue;

    @Column(nullable = false)
    private TFCode isRepeatWen;

    @Column(nullable = false)
    private TFCode isRepeatThu;

    @Column(nullable = false)
    private TFCode isRepeatFri;

    @Column(nullable = false)
    private TFCode isRepeatSat;

    @Column(nullable = false)
    private TFCode isRepeatSun;

    @Column(nullable = false)
    private TFCode isChecked;

    @Builder
    public Todo(User user, Goal goal, Integer orderNo, String contents, LocalDate date,
        LocalDate startRepeatDate, LocalDate endRepeatDate, Boolean isRepeatMon,
        Boolean isRepeatTue, Boolean isRepeatWen, Boolean isRepeatThu, Boolean isRepeatFri,
        Boolean isRepeatSat, Boolean isRepeatSun) {
        this.user = user;
        this.goal = goal;
        this.orderNo = orderNo;
        this.contents = contents;
        this.date = date;
        this.startRepeatDate = startRepeatDate;
        this.endRepeatDate = endRepeatDate;
        this.isRepeatMon = TFCode.boolValueOf(isRepeatMon);
        this.isRepeatTue = TFCode.boolValueOf(isRepeatTue);
        this.isRepeatWen = TFCode.boolValueOf(isRepeatWen);
        this.isRepeatThu = TFCode.boolValueOf(isRepeatThu);
        this.isRepeatFri = TFCode.boolValueOf(isRepeatFri);
        this.isRepeatSat = TFCode.boolValueOf(isRepeatSat);
        this.isRepeatSun = TFCode.boolValueOf(isRepeatSun);
        this.isChecked = TFCode.FALSE;
    }

    public void update(TodoUpdateWithoutOrderAndGoalRequest params) {
        if (!CommonUtils.isEmpty(params.getContents())) {
            this.contents = params.getContents();
        }
        if (!CommonUtils.isEmpty(params.getDate())) {
            this.date = params.getDate();
        }
        if (!CommonUtils.isEmpty(params.getStartRepeatDate())) {
            this.startRepeatDate = params.getStartRepeatDate();
        }
        if (!CommonUtils.isEmpty(params.getEndRepeatDate())) {
            this.endRepeatDate = params.getEndRepeatDate();
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatMon())) {
            this.isRepeatMon = TFCode.boolValueOf(params.getIsRepeatMon());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatTue())) {
            this.isRepeatTue = TFCode.boolValueOf(params.getIsRepeatTue());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatWen())) {
            this.isRepeatWen = TFCode.boolValueOf(params.getIsRepeatWen());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatThu())) {
            this.isRepeatThu = TFCode.boolValueOf(params.getIsRepeatThu());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatFri())) {
            this.isRepeatFri = TFCode.boolValueOf(params.getIsRepeatFri());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatSat())) {
            this.isRepeatSat = TFCode.boolValueOf(params.getIsRepeatSat());
        }
        if (!CommonUtils.isEmpty(params.getIsRepeatSun())) {
            this.isRepeatSun = TFCode.boolValueOf(params.getIsRepeatSun());
        }
        if (!CommonUtils.isEmpty(params.getIsChecked())) {
            this.isChecked = TFCode.boolValueOf(params.getIsChecked());
        }
    }

    public Boolean getIsChecked() {
        return this.isChecked.isBoolValue();
    }

    public void updateOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Todo todo = (Todo) o;
        return id != null && Objects.equals(id, todo.id);
    }
}