package com.ndex.clonemate.like.domain;

import com.ndex.clonemate.user.domain.mapper.UserResponseMapping;

public interface LikeResponseMapping {
    Long getId();
    UserResponseMapping getUser();
}
