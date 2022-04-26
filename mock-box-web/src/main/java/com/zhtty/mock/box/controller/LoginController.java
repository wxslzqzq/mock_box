package com.zhtty.mock.box.controller;

import com.zhtty.mock.box.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public boolean selectApplication(String userNo, String password) {
        loginService.loginByToken(userNo, password);
        return true;
    }
}
