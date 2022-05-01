package com.ndex.clonemate.user.web;

import com.ndex.clonemate.user.service.UserService;
import com.ndex.clonemate.user.web.dto.UserRegisterRequestDto;
import com.ndex.clonemate.user.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //// 사용자 회원가입 ////
    @PostMapping("/users")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDto requestDto) {
        return userService.register(requestDto);
    }

    //// 사용자 정보 관련 ////
    @GetMapping("/users/{id}")
    public ResponseEntity<?> myPage(@PathVariable(name = "id") Long id) {
        return userService.findByUserId(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return userService.delete(id);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto,
                                    @PathVariable(name = "id") Long id) {
        return userService.update(id, userUpdateRequestDto);
    }

    //// 사용자 중복 체크 ////
    @GetMapping("/users/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/users/account/{account}")
    public ResponseEntity<?> findUserByAccount(@PathVariable String account) {
        return userService.findUserByAccount(account);
    }

}
