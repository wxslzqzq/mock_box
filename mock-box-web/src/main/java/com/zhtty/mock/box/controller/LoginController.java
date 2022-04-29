package com.zhtty.mock.box.controller;

import com.zhtty.mock.box.constants.MockBoxConst;
import com.zhtty.mock.box.model.in.LoginRequest;
import com.zhtty.mock.box.model.out.LoginResponse;
import com.zhtty.mock.box.model.out.LogoutResponse;
import com.zhtty.mock.box.service.LoginService;
import com.zhtty.mock.box.shiro.JWTUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public LoginResponse loginByPwd(@RequestBody LoginRequest in) {
        return loginService.loginByPwd(in.getUsername(), in.getPassword());
    }

    @PostMapping("/logout")
    @ApiOperation(value = "会话注销")
    public LogoutResponse logout(@RequestHeader(MockBoxConst.LOGIN_SIGN) String token) {
        String userNo = JWTUtil.getUserNo(token);
        log.info("user {} logout", userNo);
        return LogoutResponse.builder()
                .userNo(userNo)
                .build();
    }
}
