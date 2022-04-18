package com.ndex.clonemate.todo.domain.repository;

import com.ndex.clonemate.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    <T> Optional<T> findById(Class<T> type, Long id);

    //체크한 것은 아래로, order 순서대로 정렬
    <T> List<T> findByUser_IdAndStartRepeatDateLessThanEqualAndEndRepeatDateGreaterThanEqualOrderByCheckYnAscOrderNoAsc(Class<T> type, Long userId, LocalDate todayStartdate, LocalDate totdyEndDate);
}