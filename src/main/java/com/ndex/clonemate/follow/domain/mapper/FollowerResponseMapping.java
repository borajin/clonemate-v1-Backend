package com.ndex.clonemate.follow.domain.mapper;

import com.ndex.clonemate.user.domain.UserResponseMapping;

//나를 팔로우 한 사람들
public interface FollowerResponseMapping {
    Long getId();
    UserResponseMapping getUser();
}
