package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.ActionDO;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.model.out.GetUserInfoResponse;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.ActionService;
import com.zhtty.mock.box.service.PermissionGroupService;
import com.zhtty.mock.box.service.RoleService;
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
    private PermissionGroupService permissionGroupService;

    @Autowired
    private ActionService actionService;

    @Override
    public UserDO getPrincipal(String userNo) {
        return userRepository.selectOneByUserNo(userNo)
                .orElseThrow(() -> new BizException(ExceptionMessageEnum.USER_IS_NOT_EXIST));
    }

    @Override
    public Set<String> getPermissions(String roleIdsString) {
        Set<ActionDO> actions = getActionsSet(roleIdsString);
        return actions.stream().map(ActionDO::getAction).collect(Collectors.toSet());
    }

    @Override
    public Set<ActionDO> getActionsSet(String roleIdsString) {
        if (StrUtil.isEmpty(roleIdsString)) {
            return Collections.emptySet();
        }
        List<Long> roleIds = JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, roleIdsString);
        Set<RoleDO> roleSet = roleService.getRolesByIds(roleIds);
        if (CollectionUtil.isEmpty(roleSet)) {
            return Collections.emptySet();
        }
        Set<Long> permissionGroupIds = new HashSet<>(100);
        roleSet.forEach(role -> permissionGroupIds.addAll(Objects.requireNonNull(JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, role.getPermissionGroupIdList()))));
        if (CollectionUtil.isEmpty(permissionGroupIds)) {
            return Collections.emptySet();
        }
        Set<ActionDO> actions = new HashSet<>(actionService.getActionsByIds(permissionGroupIds));
        if (CollectionUtil.isEmpty(actions)) {
            return Collections.emptySet();
        }
        return actions;
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
}
