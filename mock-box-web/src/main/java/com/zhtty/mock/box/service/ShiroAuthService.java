package com.zhtty.mock.box.service;

import com.zhtty.mock.box.dao.model.UserDO;

import java.util.List;

public interface ShiroAuthService {
    UserDO getPrincipal(String userNo);

    List<String> getPermissions(Long id);
}
