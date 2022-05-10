package com.ndex.clonemate.follow.service;

import com.ndex.clonemate.follow.domain.mapper.FollowerResponseMapping;
import com.ndex.clonemate.follow.domain.mapper.FollowingResponseMapping;

import java.util.List;

public interface FollowService {

    List<FollowerResponseMapping> getFollowers(Long userId);

    List<FollowingResponseMapping> getFollowings(Long userId);

    void follow(Long userId, Long followId);

    void unFollow(Long id);
}
