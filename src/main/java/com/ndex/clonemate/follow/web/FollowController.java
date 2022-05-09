

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
        Long userId = token.getId();
        return ApiUtils.createSuccessApi(followService.getFollowers(userId));
    }

    @GetMapping("/followings")
    public ApiResult<?> getFollowings(CustomAuthenticationToken token) {
        Long userId = token.getId();
        return ApiUtils.createSuccessApi(followService.getFollowings(userId));
    }

    @PostMapping("/{id}")
    public ApiResult<?> follow(CustomAuthenticationToken token, @PathVariable("id") Long followId) {
        Long userId = token.getId();
        followService.follow(userId, followId);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> unFollow(@PathVariable("id") Long unFollowId) {
        followService.unFollow(unFollowId);
        return ApiUtils.createSuccessEmptyApi();
    }
}
