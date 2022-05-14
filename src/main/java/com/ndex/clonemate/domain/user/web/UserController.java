package com.ndex.clonemate.domain.user.web;

import com.ndex.clonemate.domain.user.service.UserService;
import com.ndex.clonemate.domain.user.web.dto.UserRegisterRequestDto;
import com.ndex.clonemate.domain.user.web.dto.UserUpdateRequestDto;
import com.ndex.clonemate.global.dto.ApiResult;
import com.ndex.clonemate.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //// 사용자 회원가입 ////
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<?> register(@RequestBody UserRegisterRequestDto requestDto) {
        return ApiUtils.createSuccessApi(userService.register(requestDto));
    }

    //// 사용자 정보 관련 ////
    @GetMapping("/users/{id}")
    public ApiResult<?> myPage(@PathVariable(name = "id") Long id) {
        return ApiUtils.createSuccessApi(userService.findByUserId(id));
    }

    @DeleteMapping("/users/{id}")
    public ApiResult<?> delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return ApiUtils.createSuccessEmptyApi();
    }

    @PatchMapping("/users/{id}")
    public ApiResult<?> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto,
                               @PathVariable(name = "id") Long id) {
        userService.update(id, userUpdateRequestDto);
        return ApiUtils.createSuccessEmptyApi();
    }

    //// 사용자 중복 체크 ////
    @GetMapping("/users/email/{email}")
    public ApiResult<?> haveUserByEmail(@PathVariable String email) {
        return ApiUtils.createSuccessApi(userService.haveUserByEmail(email));
    }

    @GetMapping("/users/account/{account}")
    public ApiResult<?> haveUserByAccount(@PathVariable String account) {
        return ApiUtils.createSuccessApi(userService.haveUserByAccount(account));
    }
}
