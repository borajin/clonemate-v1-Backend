package com.ndex.clonemate.domain.likes.repository;

import com.ndex.clonemate.domain.user.repository.UserResponseMapping;

public interface LikeResponseMapping {

    Long getId();

    UserResponseMapping getUser();
}
