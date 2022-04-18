package com.ndex.clonemate.user.domain.repository;

import com.ndex.clonemate.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> Optional<T> findById(Class<T> type, @Param("id") Long id);
}
