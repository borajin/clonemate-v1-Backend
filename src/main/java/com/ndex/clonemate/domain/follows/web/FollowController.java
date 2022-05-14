

package com.ndex.clonemate.domain.follows.web;

import com.ndex.clonemate.domain.follows.service.FollowService;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.global.dto.ApiResult;
import com.ndex.clonemate.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/follows")
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
    public ApiResult<?> follow(CustomAuthenticationToken token, @RequestBody Long followUserId) {
        Long sessionUserId = token.getId();
        followService.follow(sessionUserId, followUserId);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> unFollow(@PathVariable("id") Long id) {
        followService.unFollow(id);
        return ApiUtils.createSuccessEmptyApi();
    }
}
