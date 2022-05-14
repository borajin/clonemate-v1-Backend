package com.ndex.clonemate.domain.todos.repository;

import com.ndex.clonemate.domain.todos.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    <T> Optional<T> findById(Class<T> type, Long id);

    <T> Optional<T> findByUser_IdAndId(Class<T> type, Long userId, Long id);

    void deleteByUser_IdAndId(Long userId, Long id);
}