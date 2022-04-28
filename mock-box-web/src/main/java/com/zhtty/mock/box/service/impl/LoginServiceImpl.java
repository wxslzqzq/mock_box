package com.zhtty.mock.box.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zhtty.mock.box.model.out.LoginResponse;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.LoginService;
import com.zhtty.mock.box.shiro.JWTToken;
import com.zhtty.mock.box.shiro.JWTUtil;
import com.zhtty.mock.box.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse loginByPwd(String userNo, String password) {
        if (StrUtil.isEmpty(userNo) && StrUtil.isEmpty(password)) {
            throw new BizException(ExceptionMessageEnum.USER_NO_OR_PASSWORD_IS_NULL);
        }
        UserDO userDO = userRepository.selectOneByUserNo(userNo).orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
        String salt = EncryptUtils.getSalt(userNo, password);
        String loginHashedPassword = EncryptUtils.getHashedPassword(password, salt);
        if (!loginHashedPassword.equals(userDO.getPassword()))
            throw new BizException(ExceptionMessageEnum.USER_NO_OR_PASSWORD_IS_ERROR);
        String sign = JWTUtil.sign(userNo, loginHashedPassword);
        Subject subject = SecurityUtils.getSubject();
        JWTToken token = new JWTToken(sign);
        try {
            subject.login(token);
        } catch (Exception e) {
            throw new BizException(ExceptionMessageEnum.AUTHENTICATION_FAIL);
        }
        return LoginResponse.builder()
                .flag(true)
                .userNo(userNo)
                .username(userDO.getUserName())
                .token(sign)
                .build();
    }
}
