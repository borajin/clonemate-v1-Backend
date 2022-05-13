package com.ndex.clonemate.follow.domain.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndex.clonemate.user.domain.UserResponseMapping;

public interface FollowingResponseMapping {

    Long getId();

    default UserResponseMapping getFollowing() {
        return getTarget();
    }

    @JsonIgnore
    UserResponseMapping getTarget();
}
