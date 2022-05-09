package com.ndex.clonemate.goal.domain.repository;

import com.ndex.clonemate.goal.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//GoalRepositoryCustom 도 extends 해주면 GoalRepository 로 둘 다 사용할 수 있음.
public interface GoalRepository extends JpaRepository<Goal, Long>, GoalRepositoryCustom {
    <T> Optional<T> findById(Class<T> type, Long id);
    <T> List<T> findByUser_IdOrderByOrderNoAsc(Class<T> type, Long userId);
}
