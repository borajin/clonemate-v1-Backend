package com.ndex.clonemate.like.web;

import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.like.service.LikeService;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{id}")
    public ApiResult<?> like(CustomAuthenticationToken token, @PathVariable("id") Long todoId) {
        Long userId = token.getId();
        likeService.like(todoId, userId);
        return ApiUtils.createSuccessEmptyApi();
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> unLike(@PathVariable("id") Long id) {
        likeService.unLike(id);
        return ApiUtils.createSuccessEmptyApi();
    }
}
