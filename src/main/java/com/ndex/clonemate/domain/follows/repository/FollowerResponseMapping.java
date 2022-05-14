package com.ndex.clonemate.domain.follows.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndex.clonemate.domain.user.repository.UserResponseMapping;

public interface FollowerResponseMapping {

    Long getId();

    default UserResponseMapping getFollower() {
        return getUser();
    }

    @JsonIgnore
    UserResponseMapping getUser();
}
