package com.zhtty.mock.box.service.impl;

import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.dao.model.UserDO;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.ShiroAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiroAuthServiceImpl implements ShiroAuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDO getPrincipal(String userNo) {
        return userRepository.selectOneByUserNo(userNo)
                .orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
    }

    @Override
    public List<String> getPermissions(Long id) {
        return null;
    }
}
