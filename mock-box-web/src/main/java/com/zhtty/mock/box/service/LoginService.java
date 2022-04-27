package com.zhtty.mock.box.service;

import com.zhtty.mock.box.dao.model.out.LoginResponse;

public interface LoginService {
    LoginResponse loginByPwd(String userNo, String password);
}
