package com.zhtty.mock.box.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.dao.mapper.PermissionMapper;
import com.zhtty.mock.box.model.PermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepository implements IRepository<Long, PermissionDO> {
    @Autowired
    private PermissionMapper mapper;

    @Override
    public Optional<PermissionDO> selectById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Long insert(PermissionDO permissionDO) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKey(Long id, PermissionDO permissionDO) {
        return false;
    }

    public List<PermissionDO> selectBatchIds(Collection<? extends Serializable> ids) {
        if (CollectionUtil.isEmpty(ids))
            return Collections.emptyList();
        return mapper.selectBatchIds(ids);
    }
}
