package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.model.PermissionGroupDO;
import com.zhtty.mock.box.repository.PermissionGroupRepository;
import com.zhtty.mock.box.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Override
    public List<PermissionGroupDO> getPermissionGroupsById(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return permissionGroupRepository.selectByBatchIds(ids);
    }
}
