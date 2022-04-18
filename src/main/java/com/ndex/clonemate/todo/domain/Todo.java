package com.ndex.clonemate.todo.domain;

import com.ndex.clonemate.goal.domain.Goal;
import com.ndex.clonemate.like.domain.Like;
import com.ndex.clonemate.todo.web.dto.TodoUpdateRequestDto;
import com.ndex.clonemate.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)   //외래키와 매핑되는 필드 명시
    private Set<Like> likes = new HashSet<>();

    @Column(name = "order_no", nullable = false)
    private Long orderNo;

    @Column(nullable = false)
    private String title;

    //null = 등록 안함
    private LocalDate date;

    @Column(name = "start_repeat_date",nullable = false)
    private LocalDate startRepeatDate;

    @Column(name = "end_repeat_date", nullable = false)
    private LocalDate endRepeatDate;

    @Size(max = 1)
    @Column(name = "repeat_mon_yn", nullable = false)
    private String repeatMonYn;

    @Size(max = 1)
    @Column(name = "repeat_tue_yn", nullable = false)
    private String repeatTueYn;

    @Size(max = 1)
    @Column(name = "repeat_wen_yn", nullable = false)
    private String repeatWenYn;

    @Size(max = 1)
    @Column(name = "repeat_thu_yn", nullable = false)
    private String repeatThuYn;

    @Size(max = 1)
    @Column(name = "repeat_fri_yn", nullable = false)
    private String repeatFriYn;

    @Size(max = 1)
    @Column(name = "repeat_sat_yn", nullable = false)
    private String repeatSatYn;

    @Size(max = 1)
    @Column(name = "repeat_sun_yn", nullable = false)
    private String repeatSunYn;

    @Size(max = 1)
    @Column(name = "check_yn", nullable = false)
    private String checkYn;   //oracle 에는 boolean 이 없음. 다른 db 호환 위해 char 형 사용하기)

    //userId 를 생성하지 않기 위해 직접 빌더 만들어줌. (builder.default 로 따로 명시 안하면 기본값 0, null, false )
    @Builder
    public Todo(User user, Goal goal, Long orderNo, String title, LocalDate date, LocalDate startRepeatDate, LocalDate endRepeatDate, String repeatMonYn, String repeatTueYn, String repeatWenYn, String repeatThuYn, String repeatFriYn, String repeatSatYn, String repeatSunYn, String checkYn) {
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
        this.checkYn = checkYn;
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

    public void updateOrder(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getGoalId() {
        return this.goal.getId();
    }

    public HashMap<String, String> getRepeatDays() {
        HashMap<String, String> repeatDays = new HashMap<>();
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