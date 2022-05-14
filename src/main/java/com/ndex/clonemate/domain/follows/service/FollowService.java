package com.ndex.clonemate.domain.follows.service;

import com.ndex.clonemate.domain.follows.repository.FollowingResponseMapping;
import com.ndex.clonemate.domain.follows.repository.FollowerResponseMapping;

import java.util.List;

public interface FollowService {

    List<FollowerResponseMapping> getFollowers(Long userId);

    List<FollowingResponseMapping> getFollowings(Long userId);

    void follow(Long userId, Long followId);

    void unFollow(Long id);
}
