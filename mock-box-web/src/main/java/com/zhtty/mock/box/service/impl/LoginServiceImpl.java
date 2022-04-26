package com.zhtty.mock.box.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public void loginByToken(String userNo, String password) {
        if (StrUtil.isEmpty(userNo) && StrUtil.isEmpty(password)) {
            throw new BizException(ExceptionMessageEnum.USER_NO_OR_PASSWORD_IS_NULL);
        }

    }
}
