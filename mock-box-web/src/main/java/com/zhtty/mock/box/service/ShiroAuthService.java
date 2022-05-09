package com.zhtty.mock.box.service;

import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.upms.GetUserInfoResponse;
import com.zhtty.mock.box.model.out.upms.RoutesResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ShiroAuthService {
    UserDO getPrincipal(String userNo);

    Set<String> getPermissions(String roleIds);

    GetUserInfoResponse getUserInfo(String token) throws IOException;

    List<RoutesResponse> getUserRoutes(String userNo);
}
