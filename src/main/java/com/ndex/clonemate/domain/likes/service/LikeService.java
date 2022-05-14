package com.ndex.clonemate.domain.likes.service;

import com.ndex.clonemate.domain.likes.model.Like;

import java.time.LocalDate;
import java.util.List;

public interface LikeService {

    List<Like> getLikeToDate(LocalDate date);

    void like(Long todoId, Long userId);

    void unLike(Long id);
}