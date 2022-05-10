package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.domain.TFCode;
import com.ndex.clonemate.todo.web.dto.TodoOverviewResponseDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateOrderAndGoalRequestDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateWithoutOrderAndGoalRequestDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateOrDeleteRequestDto;

import com.ndex.clonemate.utils.CommonUtils;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static com.ndex.clonemate.todo.domain.QTodo.todo;

@RequiredArgsConstructor
@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<TodoOverviewResponseDto> findTodoOverview(Long userId, YearMonth dateYm) {
        //"SELECT DAY(todo.date),COUNT(todo.id), CASE WHEN SUM(CASE WHEN todo.checkYn = 'y' THEN 0 ELSE 1 END) = 0 THEN 'y' ELSE 'n' END FROM Todo todo WHERE todo.user.getId() = 1 AND SUBSTRING(todo.date, 1, 7) = '"+ dateYm +"' GROUP BY todo.id";

        NumberExpression<Integer> isAllTodoCheckedCountCase = new CaseBuilder()
            .when(todo.isChecked.eq(TFCode.TRUE)).then(0)
            .otherwise(1);

        BooleanExpression isAllTodoCheckedYnCase = new CaseBuilder()
            .when(isAllTodoCheckedCountCase.sum().eq(0)).then(TFCode.TRUE.isBoolValue())
            .otherwise(TFCode.FALSE.isBoolValue());

        return jpaQueryFactory
            .select(Projections.constructor(TodoOverviewResponseDto.class,
                todo.date.dayOfMonth().as("numTodoDay"),
                todo.id.count().as("numTodoCount"),
                isAllTodoCheckedYnCase.as("isCompleted")))
            .from(todo)
            .where(
                eqUserId(userId),
                eqDateYm(dateYm)
            )
            .groupBy(todo.date)
            .orderBy(todo.date.asc())
            .fetch();
    }

    @Override
    public void updateTodos(Long userId, TodoUpdateOrDeleteRequestDto condition,
        TodoUpdateWithoutOrderAndGoalRequestDto params) {
        UpdateClause<JPAUpdateClause> updateBuilder = jpaQueryFactory.update(todo);

        if (!CommonUtils.isEmpty(params.getDate())) {
            updateBuilder.set(todo.date, params.getDate());
        }

        updateBuilder.where(
                eqUserId(userId),
                eqDate(condition.getDate()),
                eqIsChecked(condition.getIsChecked())
            )
            .execute();

        //영속성 유지
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void updateOrderOrGoal(Long userId, List<TodoUpdateOrderAndGoalRequestDto> params) {
        //쿼리 생성
        StringBuilder updateOrderQuery = new StringBuilder(
            "UPDATE Todo SET ");
        StringBuilder updateGoalIdQuery = new StringBuilder();

        updateOrderQuery.append("order_no = CASE");
        updateGoalIdQuery.append(", goal_id = CASE");

        params.forEach(item -> {
            updateOrderQuery.append(" WHEN id = ").append(item.getId()).append(" THEN ")
                .append(item.getOrderNo());
            updateGoalIdQuery.append(" WHEN id = ").append(item.getId()).append(" THEN ")
                .append(item.getGoalId());
        });

        updateOrderQuery.append(" ELSE order_no END");
        updateGoalIdQuery.append(" ELSE goal_id END");

        updateOrderQuery.append(updateGoalIdQuery).append(" WHERE user_id = ").append(userId);

        entityManager.createQuery(updateOrderQuery.toString())
            .executeUpdate();

        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteTodos(Long userId, TodoUpdateOrDeleteRequestDto condition) {
        jpaQueryFactory.delete(todo)
            .where(eqUserId(userId),
                eqDate(condition.getDate()),
                eqIsChecked(condition.getIsChecked()))
            .execute();
    }

    private BooleanExpression eqUserId(Long userId) {
        if (CommonUtils.isEmpty(userId)) {
            return null;
        }
        return todo.user.id.eq(userId);
    }

    private BooleanExpression eqDate(LocalDate date) {
        if (CommonUtils.isEmpty(date)) {
            return null;
        }
        return todo.date.eq(date);
    }

    private BooleanExpression eqDateYm(YearMonth dateYm) {
        if (CommonUtils.isEmpty(dateYm)) {
            return null;
        }
        return todo.date.year().eq(dateYm.getYear())
            .and(todo.date.month().eq(dateYm.getMonthValue()));
    }

    private BooleanExpression eqIsChecked(TFCode isChecked) {
        System.out.println(todo.isChecked + "," + isChecked);
        if (CommonUtils.isEmpty(isChecked)) {
            return null;
        }
        return todo.isChecked.eq(isChecked);
    }

    private BooleanExpression beforeEndRepeatDate() {
        return todo.endRepeatDate.before(todo.date);
    }

    private BooleanExpression isRepeatDay() {
        int dayOfWeek = Integer.parseInt(todo.date.dayOfWeek().toString());

        switch (dayOfWeek) {
            case 1:
                return todo.isRepeatMon.eq(TFCode.TRUE);
            case 2:
                return todo.isRepeatTue.eq(TFCode.TRUE);
            case 3:
                return todo.isRepeatWen.eq(TFCode.TRUE);
            case 4:
                return todo.isRepeatThu.eq(TFCode.TRUE);
            case 5:
                return todo.isRepeatFri.eq(TFCode.TRUE);
            case 6:
                return todo.isRepeatSat.eq(TFCode.TRUE);
            default:
                return todo.isRepeatSun.eq(TFCode.TRUE);
        }
    }
}
