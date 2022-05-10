package com.ndex.clonemate.like.service;

import com.ndex.clonemate.like.domain.Like;

import java.time.LocalDate;
import java.util.List;

public interface LikeService {

    List<Like> getLikeToDate(LocalDate date);

    void like(Long todoId, Long userId);

    void unLike(Long id);
}