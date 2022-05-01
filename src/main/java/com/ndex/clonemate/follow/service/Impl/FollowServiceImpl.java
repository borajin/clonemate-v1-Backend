package com.ndex.clonemate.follow.service.Impl;

import com.ndex.clonemate.follow.domain.Follow;
import com.ndex.clonemate.follow.domain.repository.FollowRepository;
import com.ndex.clonemate.follow.domain.mapper.FollowerResponseMapping;
import com.ndex.clonemate.follow.domain.mapper.FollowingResponseMapping;
import com.ndex.clonemate.follow.service.FollowService;
import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {
    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Override
    public List<FollowerResponseMapping> getFollowers(Long userId) {
        return followRepository.findByFollower_Id(FollowerResponseMapping.class, userId);
    }

    @Override
    public List<FollowingResponseMapping> getFollowings(Long userId) {
        return followRepository.findByUser_Id(FollowingResponseMapping.class, userId);
    }

    @Override
    @Transactional
    public void follow(Long userId, Long followId) {
        User user = userRepository.findById(User.class, userId).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER + "id : " + userId));
        User follower = userRepository.findById(User.class, followId).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER + "id : " + followId));

        Follow follow = Follow.builder().user(user).follower(follower).build();
        followRepository.save(follow);
    }

    @Override
    @Transactional
    public void unFollow(Long id) {
        followRepository.deleteById(id);
    }
}
