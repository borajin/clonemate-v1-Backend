package com.ndex.clonemate.domain.follows.service.Impl;

import com.ndex.clonemate.domain.follows.model.Follow;
import com.ndex.clonemate.domain.follows.repository.FollowRepository;
import com.ndex.clonemate.domain.follows.repository.FollowerResponseMapping;
import com.ndex.clonemate.domain.follows.repository.FollowingResponseMapping;
import com.ndex.clonemate.domain.follows.service.FollowService;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
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
        return followRepository.findByTarget_Id(FollowerResponseMapping.class, userId);
    }

    @Override
    public List<FollowingResponseMapping> getFollowings(Long userId) {
        return followRepository.findByUser_Id(FollowingResponseMapping.class, userId);
    }

    @Override
    @Transactional
    public void follow(Long userId, Long followId) {
        User user = userRepository.findById(User.class, userId)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER + "id : " + userId));
        User target = userRepository.findById(User.class, followId)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER + "id : " + followId));

        Follow follow = Follow.builder().user(user).target(target).build();
        followRepository.save(follow);
    }

    @Override
    @Transactional
    public void unFollow(Long id) {
        followRepository.deleteById(id);
    }
}
