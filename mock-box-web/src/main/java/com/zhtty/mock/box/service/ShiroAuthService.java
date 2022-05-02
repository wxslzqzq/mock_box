package com.zhtty.mock.box.service;

import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;

import java.util.List;
import java.util.Set;

public interface ShiroAuthService {
    UserDO getPrincipal(String userNo);

    Set<String> getPermissions(String roleIds);

    GetUserInfoResponse getUserInfo(String token);

    List<RoutesResponse> getUserRoutes(String userNo);
}
