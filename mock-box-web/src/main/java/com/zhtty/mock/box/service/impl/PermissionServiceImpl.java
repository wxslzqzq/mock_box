package com.zhtty.mock.box.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.model.PermissionDO;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.repository.PermissionRepository;
import com.zhtty.mock.box.service.PermissionService;
import com.zhtty.mock.box.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<PermissionDO> getPermissionsByIds(Set<RoleDO> roles) {
        Set<Long> permissionIds = new HashSet<>(roles.size());
        roles.forEach(roleDO -> {
            if (StrUtil.isNotEmpty(roleDO.getPermissions())) {
                permissionIds.addAll(Objects.requireNonNull(JsonUtils.readValue(new TypeReference<List<Long>>() {
                }, roleDO.getPermissions())));
            }
        });
        return new HashSet<>(permissionRepository.selectBatchIds(permissionIds));
    }
}
