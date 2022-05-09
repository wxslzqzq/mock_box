package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.PermissionDO;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.upms.*;
import com.zhtty.mock.box.repository.ActionRepository;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.*;
import com.zhtty.mock.box.shiro.JWTUtil;
import com.zhtty.mock.box.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShiroAuthServiceImpl implements ShiroAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ActionService actionService;

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
        if (CollectionUtil.isEmpty(roleSet)) throw new BizException(ExceptionMessageEnum.ROUTES_LOAD_FAIL);
        Set<PermissionDO> permissions = permissionService.getPermissionsByIds(roleSet);
       return GetUserInfoResponse.builder()
                .id(userDO.getId())
                .name(userDO.getUserName())
                .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .roles(UserRoleResponse.builder()
                        .roleName(roleSet.stream()
                                .map(RoleDO::getRoleName)
                                .collect(Collectors.toList())
                                .toString())
                        .alias(roleSet.stream()
                                .map(RoleDO::getAlias)
                                .collect(Collectors.toList())
                                .toString())
                        .describe("没有描述")
                        .permissions(permissions.stream().map(
                                permissionDO -> PermissionResponse.builder()
                                        .permissionName(permissionDO.getPermissionName())
                                        .permissionId(permissionDO.getPermissionId())
                                        .actionEntitySet(actionService.getActionsByIds(JsonUtils.readValue(new TypeReference<List<Long>>() {
                                        }, permissionDO.getActions())))
                                        .build()
                        ).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @SneakyThrows
    public GetUserInfoResponse mock() {

        String path = "/init/permissions.json";
        InputStream inputStream = getClass().getResourceAsStream(path);
        log.info("————————开始读取权限配置初始化的文件————————");
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        return JsonUtils.readValue(new TypeReference<GetUserInfoResponse>() {
        }, bytes);
    }

    @Override
    public List<RoutesResponse> getUserRoutes(String userNo) {
        UserDO userDO = userRepository.selectOneByUserNo(userNo).orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
        List<Long> roleIds = JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, userDO.getRoleIdList());
        return routeService.getRoutesByRoleIds(roleIds);
    }
}
