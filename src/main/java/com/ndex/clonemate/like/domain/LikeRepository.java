package com.ndex.clonemate.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByTodo_Date(LocalDate date);
}
