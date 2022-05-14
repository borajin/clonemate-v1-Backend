package com.ndex.clonemate.domain.goals.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class GoalRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
}
