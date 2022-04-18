package com.ndex.clonemate.user.controller;

import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.repository.UserRepository;
import com.ndex.clonemate.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            List<User> data = userRepository.findAll();

            ApiResult<List<User>> response = ApiResult.<List<User>>builder().success(true).data(data).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResult<String> response = ApiResult.<String>builder().success(false).errorMessage(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
