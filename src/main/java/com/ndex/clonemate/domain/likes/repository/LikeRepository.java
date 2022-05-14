package com.ndex.clonemate.domain.likes.repository;

import com.ndex.clonemate.domain.likes.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByTodo_Date(LocalDate date);
}
