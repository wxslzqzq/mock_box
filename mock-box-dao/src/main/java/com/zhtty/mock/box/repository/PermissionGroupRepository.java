package com.zhtty.mock.box.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.dao.mapper.PermissionGroupMapper;
import com.zhtty.mock.box.model.PermissionGroupDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PermissionGroupRepository implements IRepository<Long, PermissionGroupDO> {
    @Autowired
    private PermissionGroupMapper permissionGroupMapper;

    @Override
    public Optional<PermissionGroupDO> selectById(Long aLong) {
        return Optional.ofNullable(permissionGroupMapper.selectById(aLong));
    }

    @Override
    public Long insert(PermissionGroupDO permissionGroupDO) {
        permissionGroupMapper.insert(permissionGroupDO);
        return permissionGroupDO.getId();
    }

    @Override
    public boolean updateByPrimaryKey(Long id, PermissionGroupDO permissionGroupDO) {
        permissionGroupDO.setId(id);
        return permissionGroupMapper.updateById(permissionGroupDO) == 1;
    }

    public List<PermissionGroupDO> selectByBatchIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return permissionGroupMapper.selectBatchIds(ids);
    }
}
