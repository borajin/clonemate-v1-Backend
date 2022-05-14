package com.ndex.clonemate.domain.goals.repository;

import com.ndex.clonemate.domain.goals.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    <T> Optional<T> findById(Class<T> type, Long id);

    <T> Optional<T> findByUser_IdAndId(Class<T> type, Long userId, Long id);

    <T> List<T> findByUser_IdOrderByOrderNoAsc(Class<T> type, Long userId);

    void deleteByUser_IdAndId(Long userId, Long id);
}
