package com.ndex.clonemate.follow.domain.mapper;

import com.ndex.clonemate.user.domain.UserResponseMapping;

public interface FollowingResponseMapping {

    Long getId();

    UserResponseMapping getFollowing();
}
