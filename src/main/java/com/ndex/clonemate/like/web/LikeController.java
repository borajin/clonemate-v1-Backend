package com.ndex.clonemate.like.web;

import com.ndex.clonemate.like.service.LikeService;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{id}")
    public ResponseEntity<?> like(@PathVariable("id") Long todoId) {
        try {
            likeService.like(todoId, 1L);
            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> unLike(@PathVariable("id") Long id) {
        try {
            likeService.unLike(id);
            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
