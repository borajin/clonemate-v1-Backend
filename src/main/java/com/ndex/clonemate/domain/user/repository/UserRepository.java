package com.ndex.clonemate.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> Optional<T> findById(Class<T> type, @Param("id") Long id);

    @Query("select u from User u where u.id = :id and u.deleteYn = 'N'")
    Optional<User> findByUserId(@Param("id") Long id);

    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);
}
