package com.zhtty.mock.box.service;

import com.zhtty.mock.box.model.ActionDO;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;

import java.util.List;
import java.util.Set;

public interface ShiroAuthService {
    UserDO getPrincipal(String userNo);

    Set<String> getPermissions(String roleIds);
    Set<ActionDO> getActionsSet(String roleId);
    GetUserInfoResponse getUserInfo(String token);
}
