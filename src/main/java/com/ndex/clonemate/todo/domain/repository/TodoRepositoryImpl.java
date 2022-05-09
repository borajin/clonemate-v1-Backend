package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.web.dto.TodoOverviewResponseDto;
import com.ndex.clonemate.todo.web.dto.TodoUpdateRequestDto;
import com.ndex.clonemate.todo.web.dto.TodosCondition;
import com.querydsl.core.Tuple;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    public void updateTodos(Long userId, TodosCondition condition, TodoUpdateRequestDto params) {
        UpdateClause<JPAUpdateClause> updateBuilder = jpaQueryFactory.update(todo);

        //일자 변경
        if (params.getDate() != null) {
            updateBuilder.set(todo.date, params.getDate());
        }

        //반복일자변경
        if(params.getEndRepeatDate() != null) {
            updateBuilder.set(todo.endRepeatDate, params.getEndRepeatDate());
        }

        //condition 에 따라 어떤 투두를 바꿀지
        updateBuilder.where(
                        eqUserId(userId),
                        eqDate(condition.getDate()),
                        eqCheckYn(condition.getCheckYn())
                )
                .execute();

        //영속성 유지
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteTodos(Long userId, TodosCondition condition) {
        jpaQueryFactory.delete(todo)
                .where(eqUserId(userId),
                        eqDate(condition.getDate()),
                        eqCheckYn(condition.getCheckYn()))
                .execute();

        //TODO : 영속성 유지 해야하는지?
    }

    @Override
    public List<TodoOverviewResponseDto> findTodoOverview(Long userId, YearMonth dateYm) {
        //"SELECT DAY(todo.date),COUNT(todo.id), CASE WHEN SUM(CASE WHEN todo.checkYn = 'y' THEN 0 ELSE 1 END) = 0 THEN 'y' ELSE 'n' END FROM Todo todo WHERE todo.user.getId() = 1 AND SUBSTRING(todo.date, 1, 7) = '"+ dateYm +"' GROUP BY todo.id";

        NumberExpression<Integer> isAllTodoCheckedCountCase = new CaseBuilder()
                .when(todo.checkYn.eq('Y')).then(0)
                .otherwise(1);

        StringExpression isAllTodoCheckedYnCase = new CaseBuilder()
                .when(isAllTodoCheckedCountCase.sum().eq(0)).then("y")
                .otherwise("n");

        List<TodoOverviewResponseDto> result = jpaQueryFactory
                .select(Projections.constructor(TodoOverviewResponseDto.class,
                        todo.date.dayOfMonth().as("numTodoDay"),
                        todo.id.count().as("numTodoCount"),
                        isAllTodoCheckedYnCase.as("ynComplete")))
                .from(todo)
                .where(
                        eqUserId(userId),
                        eqDateYm(dateYm)
                )
                .groupBy(todo.date)
                .orderBy(todo.date.asc())
                .fetch();

        return result;
    }

//    @Override
//    public void updateOrder(Long userId, Long goalId, List<UpdateTodoOrderRequestDto> params) {
//        //쿼리 생성
//        StringBuilder updateOrderQuery = new StringBuilder("UPDATE Todo SET (order_no, goal_id) = (");
//        StringBuilder updateGoalIdQuery = new StringBuilder();
//
//        for (int i = 0; i < params.size(); i++) {
//            if (i == 0) {
//                updateOrderQuery.append("CASE");
//                updateGoalIdQuery.append(", CASE");
//            }
//
//            updateOrderQuery.append(" WHEN todo_id = ").append(params.get(i).getId()).append(" THEN ").append(params.get(i).getOrderNo());
//            updateGoalIdQuery.append("todo_id = ").append(params.get(i).getId()).append(" THEN ").append(params.get(i).getGoalId());
//        }
//        updateOrderQuery.append(" ELSE order_no END");
//        updateGoalIdQuery.append(" ELSE goal_id END");
//
//        updateOrderQuery.append(updateGoalIdQuery).append(") WHERE user_id = ").append(userId);
//
//        //쿼리 실행. 두번쨰 인자는 반환 타입. update 문이므로 반환 x
//        entityManager.createQuery(updateOrderQuery.toString())
//                .executeUpdate();
//
//        //영속성 유지
//        entityManager.flush();
//        entityManager.clear();
//    }

    //아래는 where 조건을 동적으로 생성하기 위한 BooleanExpression 입니다.
    private BooleanExpression eqUserId(Long userId) {
        if (userId == null) return null;
        return todo.user.id.eq(userId);
    }

    private BooleanExpression eqDate(LocalDate date) {
        if (date == null) return null;
        return todo.date.eq(date);
    }

    private BooleanExpression eqDateYm(YearMonth dateYm) {
        if(dateYm == null) return null;
        return todo.date.year().eq(dateYm.getYear()).and(todo.date.month().eq(dateYm.getMonthValue()));
    }

    private BooleanExpression eqEndRepeatDateYm(YearMonth dateYm) {
        if(dateYm == null) return null;
        return todo.endRepeatDate.year().eq(dateYm.getYear()).and(todo.endRepeatDate.month().eq(dateYm.getMonthValue()));
    }

    private BooleanExpression eqCheckYn(Character checkYn) {
        if (checkYn == null) return null;
        return todo.checkYn.eq(checkYn);
    }

    private BooleanExpression beforeEndRepeatDate() {
        return todo.endRepeatDate.before(todo.date);
    }

    private BooleanExpression isRepeatDay() {
        int dayOfWeek = Integer.parseInt(todo.date.dayOfWeek().toString());

        switch (dayOfWeek) {
            case 1:
                return todo.repeatMonYn.eq('Y');
            case 2:
                return todo.repeatTueYn.eq('Y');
            case 3:
                return todo.repeatWenYn.eq('Y');
            case  4:
                return todo.repeatThuYn.eq('Y');
            case 5 :
                return todo.repeatFriYn.eq('Y');
            case 6 :
                return todo.repeatSatYn.eq('Y');
            default:
                return todo.repeatSunYn.eq('Y');
        }
    }
}
