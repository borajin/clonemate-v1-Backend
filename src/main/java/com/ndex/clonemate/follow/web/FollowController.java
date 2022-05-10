

package com.ndex.clonemate.follow.web;

import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.follow.service.FollowService;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/follows")
@RestController
public class FollowController {

    private final FollowService followService;

    @GetMapping("/followers")
    public ApiResult<?> getFollowers(CustomAuthenticationToken token) {
        Long sessionUserId = token.getId();
        return ApiUtils.createSuccessApi(followService.getFollowers(sessionUserId));
    }

    @GetMapping("/followings")
    public ApiResult<?> getFollowings(CustomAuthenticationToken token) {
        Long sessionUserId = token.getId();
        return ApiUtils.createSuccessApi(followService.getFollowings(sessionUserId));
    }

    @PostMapping("/{id}")
    public ApiResult<?> follow(CustomAuthenticationToken token, @PathVariable("id") Long followsId) {
        Long sessionUserId = token.getId();
        followService.follow(sessionUserId, followsId);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> unFollow(@PathVariable("id") Long followsId) {
        followService.unFollow(followsId);
        return ApiUtils.createSuccessEmptyApi();
    }
}
