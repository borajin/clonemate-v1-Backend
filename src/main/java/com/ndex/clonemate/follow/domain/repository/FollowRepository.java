package com.ndex.clonemate.follow.domain.repository;

import com.ndex.clonemate.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    <T> List<T> findByUser_Id(Class<T> type, Long userId);
    <T> List<T> findByFollower_Id(Class<T> type, Long followerId);
}