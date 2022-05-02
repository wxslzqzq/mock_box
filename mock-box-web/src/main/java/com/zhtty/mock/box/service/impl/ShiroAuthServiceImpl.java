package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.RoleService;
import com.zhtty.mock.box.service.RouteService;
import com.zhtty.mock.box.service.ShiroAuthService;
import com.zhtty.mock.box.shiro.JWTUtil;
import com.zhtty.mock.box.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShiroAuthServiceImpl implements ShiroAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RouteService routeService;


    @Override
    public UserDO getPrincipal(String userNo) {
        return userRepository.selectOneByUserNo(userNo)
                .orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
    }

    @Override
    public Set<String> getPermissions(String roleIdsString) {
//        Set<ActionDO> actions = getActionsSet(roleIdsString);
//        return actions.stream().map(ActionDO::getAction).collect(Collectors.toSet());
        return null;
    }


    @Override
    public GetUserInfoResponse getUserInfo(String token) {
        String userNo = JWTUtil.getUserNo(token);

        UserDO userDO = userRepository.selectOneByUserNo(userNo).orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
        Set<RoleDO> roleSet = roleService.getRolesByIds(JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, userDO.getRoleIdList()));
        return GetUserInfoResponse.builder()
                .name(userDO.getUserName())
                .roles(roleSet.stream()
                        .map(RoleDO::getRoleName)
                        .collect(Collectors.toList()))
                .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .build();
    }

    @Override
    public List<RoutesResponse> getUserRoutes(String userNo) {
        UserDO userDO = userRepository.selectOneByUserNo(userNo).orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
        List<Long> roleIds = JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, userDO.getRoleIdList());
        return routeService.getRoutesByRoleIds(roleIds);
    }
}
