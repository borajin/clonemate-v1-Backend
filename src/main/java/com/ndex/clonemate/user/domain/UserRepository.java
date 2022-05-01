package com.ndex.clonemate.user.domain;

import java.util.Optional;

import com.ndex.clonemate.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<com.ndex.clonemate.user.domain.User, Long> {
    <T> Optional<T> findById(Class<T> type, @Param("id") Long id);

    @Query("select u from User u where u.id = :id and u.deleteYn = 'N'")
    Optional<com.ndex.clonemate.user.domain.User> findByUserId(@Param("id") Long id);

    Optional<com.ndex.clonemate.user.domain.User> findByAccount(String account);

    Optional<User> findByEmail(String email);
}
