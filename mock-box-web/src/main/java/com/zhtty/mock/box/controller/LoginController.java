package com.zhtty.mock.box.controller;

import com.zhtty.mock.box.dao.model.in.LoginRequest;
import com.zhtty.mock.box.dao.model.out.LoginResponse;
import com.zhtty.mock.box.service.LoginService;
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
    @ApiOperation(value = "登录", notes = "")
    public LoginResponse loginByPwd(@RequestBody LoginRequest in) {
        return loginService.loginByPwd(in.getUsername(), in.getPassword());
    }
}
