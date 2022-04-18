package com.ndex.clonemate.goal.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

//반드시 구현체 이름 끝에는 Impl 가 있어야 함.
@RequiredArgsConstructor
@Repository
public class GoalRepositoryImpl implements GoalRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
}
