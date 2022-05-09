package com.ndex.clonemate.todo.domain;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.like.domain.Like;
import com.ndex.clonemate.todo.web.dto.TodoUpdateRequestDto;
import com.ndex.clonemate.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor //update, delete 에 필요.
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
    private Set<Like> likes = new HashSet<>();

    @Column(nullable = false)
    private Long orderNo;

    @Column(nullable = false)
    private String title;

    private LocalDate date;

    private LocalDate startRepeatDate;

    private LocalDate endRepeatDate;

    @Column(nullable = false)
    private Character repeatMonYn;

    @Column(nullable = false)
    private Character repeatTueYn;

    @Column(nullable = false)
    private Character repeatWenYn;

    @Column(nullable = false)
    private Character repeatThuYn;

    @Column(nullable = false)
    private Character repeatFriYn;

    @Column(nullable = false)
    private Character repeatSatYn;

    @Column(nullable = false)
    private Character repeatSunYn;

    @Column(nullable = false)
    private Character checkYn;   //oracle 에는 boolean 이 없음. 다른 db 호환 위해 char 형 사용하기)

    //userId 를 생성하지 않기 위해 직접 빌더 만들어줌. (builder.default 로 따로 명시 안하면 기본값 0, null, false )
    @Builder
    public Todo(User user, Goal goal, Long orderNo, String title, LocalDate date, LocalDate startRepeatDate, LocalDate endRepeatDate, Character repeatMonYn, Character repeatTueYn, Character repeatWenYn, Character repeatThuYn, Character repeatFriYn, Character repeatSatYn, Character repeatSunYn) {
        this.user = user;
        this.goal = goal;
        this.orderNo = orderNo;
        this.title = title;
        this.date = date;
        this.startRepeatDate = startRepeatDate;
        this.endRepeatDate = endRepeatDate;
        this.repeatMonYn = repeatMonYn;
        this.repeatTueYn = repeatTueYn;
        this.repeatWenYn = repeatWenYn;
        this.repeatThuYn = repeatThuYn;
        this.repeatFriYn = repeatFriYn;
        this.repeatSatYn = repeatSatYn;
        this.repeatSunYn = repeatSunYn;
        this.checkYn = 'N';
    }

    public void update(TodoUpdateRequestDto params) {
        if (params.getTitle() != null) this.title = params.getTitle();
        if (params.getDate() != null) this.date = params.getDate();
        if(params.getStartRepeatDate() != null) this.startRepeatDate = params.getStartRepeatDate();
        if (params.getEndRepeatDate() != null) this.endRepeatDate = params.getEndRepeatDate();
        if (params.getRepeatMonYn() != null) this.repeatMonYn = params.getRepeatMonYn();
        if (params.getRepeatTueYn() != null) this.repeatTueYn = params.getRepeatTueYn();
        if (params.getRepeatWenYn() != null) this.repeatWenYn = params.getRepeatWenYn();
        if (params.getRepeatThuYn() != null) this.repeatThuYn = params.getRepeatThuYn();
        if (params.getRepeatFriYn() != null) this.repeatFriYn = params.getRepeatFriYn();
        if (params.getRepeatSatYn() != null) this.repeatSatYn = params.getRepeatSatYn();
        if (params.getRepeatSunYn() != null) this.repeatSunYn = params.getRepeatSunYn();
        if (params.getCheckYn() != null) this.checkYn = params.getCheckYn();
    }

    public void updateOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getGoalId() {
        return this.goal.getId();
    }

    public HashMap<String, Character> getRepeatDays() {
        HashMap<String, Character> repeatDays = new HashMap<>();
        repeatDays.put("MON", this.repeatMonYn);
        repeatDays.put("TUE", this.repeatTueYn);
        repeatDays.put("WED", this.repeatWenYn);
        repeatDays.put("THU", this.repeatThuYn);
        repeatDays.put("FRI", this.repeatFriYn);
        repeatDays.put("SAT", this.repeatSatYn);
        repeatDays.put("SUN", this.repeatSunYn);

        return repeatDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return id != null && Objects.equals(id, todo.id);
    }
}