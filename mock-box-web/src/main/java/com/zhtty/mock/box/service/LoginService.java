package com.zhtty.mock.box.service;

import com.zhtty.mock.box.model.out.LoginResponse;

public interface LoginService {
    LoginResponse loginByPwd(String userNo, String password);
}
