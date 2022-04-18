package com.ndex.clonemate.follow.web;

import com.ndex.clonemate.follow.domain.mapper.FollowerResponseMapping;
import com.ndex.clonemate.follow.domain.mapper.FollowingResponseMapping;
import com.ndex.clonemate.follow.service.FollowService;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {
    private final FollowService followService;

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers() {
        try {
            List<FollowerResponseMapping> data = followService.getFollowers(1L);
            ApiResult<List<FollowerResponseMapping>> response = ApiResult.<List<FollowerResponseMapping>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/followings")
    public ResponseEntity<?> getFollowings() {
        try {
            List<FollowingResponseMapping> data = followService.getFollowings(1L);
            ApiResult<List<FollowingResponseMapping>> response = ApiResult.<List<FollowingResponseMapping>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> follow(@PathVariable("id") Long id) {
        try {
            followService.follow(1L, id);
            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> unFollow(@PathVariable("id") Long id) {
        try {
            followService.unFollow(id);
            ApiResult<String> response = ApiResult.<String>builder().success(true).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
