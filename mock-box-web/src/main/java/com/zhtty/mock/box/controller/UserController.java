package com.zhtty.mock.box.controller;

import com.zhtty.mock.box.model.in.LoginRequest;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;
import com.zhtty.mock.box.service.ShiroAuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ShiroAuthService shiroAuthService;

    @GetMapping("/get/user/info")
    @ApiOperation(value = "获取当前登录用户信息")
    public GetUserInfoResponse getUserInfo(String token) {
        return shiroAuthService.getUserInfo(token);
    }
}
