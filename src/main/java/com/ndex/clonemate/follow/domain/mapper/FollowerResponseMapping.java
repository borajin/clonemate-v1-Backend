package com.ndex.clonemate.follow.domain.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndex.clonemate.user.domain.UserResponseMapping;

public interface FollowerResponseMapping {

    Long getId();

    default UserResponseMapping getFollower() {
        return getUser();
    }

    @JsonIgnore
    UserResponseMapping getUser();
}
