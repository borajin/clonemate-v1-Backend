package com.ndex.clonemate.follow.domain.mapper;

import com.ndex.clonemate.user.domain.UserResponseMapping;

public interface FollowerResponseMapping {

    Long getId();

    UserResponseMapping getFollower();
}
