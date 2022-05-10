package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    <T> Optional<T> findById(Class<T> type, Long id);

    <T> Optional<T> findByUser_IdAndId(Class<T> type, Long userId, Long id);

    //체크한 것은 아래로, order 순서대로 정렬
    <T> List<T> findByUser_IdAndDateOrderByIsCheckedAscOrderNoAsc(Class<T> type, Long userId,
        LocalDate date);

    void deleteByUser_IdAndId(Long userId, Long id);
}