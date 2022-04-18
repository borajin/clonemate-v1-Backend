package com.ndex.clonemate.follow.domain.mapper;

import com.ndex.clonemate.user.domain.mapper.UserResponseMapping;

//내가 팔로우 한 사람들
public interface FollowingResponseMapping {
    Long getId();
    UserResponseMapping getFollower();
}
