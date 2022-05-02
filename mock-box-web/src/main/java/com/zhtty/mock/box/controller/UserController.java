package com.zhtty.mock.box.controller;

import com.zhtty.mock.box.constants.MockBoxConst;
import com.zhtty.mock.box.model.in.LoginRequest;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;
import com.zhtty.mock.box.model.out.LogoutResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;
import com.zhtty.mock.box.service.RouteService;
import com.zhtty.mock.box.service.ShiroAuthService;
import com.zhtty.mock.box.shiro.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ShiroAuthService shiroAuthService;
    @Autowired
    private RouteService routeService;

    @GetMapping("/get/user/info")
    @ApiOperation(value = "获取当前登录用户信息")
    public GetUserInfoResponse getUserInfo(@NotNull String token) {
        return shiroAuthService.getUserInfo(token);
    }

    @PostMapping("/get/user/routes")
    @ApiOperation(value = "获取当前用户许可路由")
    public List<RoutesResponse> getUserRoute(@RequestHeader(MockBoxConst.LOGIN_SIGN) String token) {
        String userNo = JWTUtil.getUserNo(token);
        return shiroAuthService.getUserRoutes(userNo);
    }
}
