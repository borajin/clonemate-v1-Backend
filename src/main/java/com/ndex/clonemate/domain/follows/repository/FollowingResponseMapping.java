package com.ndex.clonemate.domain.follows.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndex.clonemate.domain.user.repository.UserResponseMapping;

public interface FollowingResponseMapping {

    Long getId();

    default UserResponseMapping getFollowing() {
        return getTarget();
    }

    @JsonIgnore
    UserResponseMapping getTarget();
}
