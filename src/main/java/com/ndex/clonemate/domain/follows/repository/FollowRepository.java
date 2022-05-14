package com.ndex.clonemate.domain.follows.repository;

import com.ndex.clonemate.domain.follows.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    <T> List<T> findByUser_Id(Class<T> type, Long userId);

    <T> List<T> findByTarget_Id(Class<T> type, Long targetId);
}