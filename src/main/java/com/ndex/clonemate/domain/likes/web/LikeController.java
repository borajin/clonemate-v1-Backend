package com.ndex.clonemate.domain.likes.web;

import com.ndex.clonemate.domain.likes.service.LikeService;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.global.dto.ApiResult;
import com.ndex.clonemate.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/likes")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ApiResult<?> like(CustomAuthenticationToken token, @RequestBody Long todoId) {
        Long sessionUserId = token.getId();
        likeService.like(todoId, sessionUserId);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> unLike(@PathVariable("id") Long id) {
        likeService.unLike(id);
        return ApiUtils.createSuccessEmptyApi();
    }
}
