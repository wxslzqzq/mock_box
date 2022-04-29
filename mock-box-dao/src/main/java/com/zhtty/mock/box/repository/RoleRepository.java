package com.zhtty.mock.box.repository;

import com.zhtty.mock.box.dao.mapper.RoleMapper;
import com.zhtty.mock.box.model.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository implements IRepository<Long, RoleDO> {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Optional<RoleDO> selectById(Long aLong) {
        return Optional.ofNullable(roleMapper.selectById(aLong));
    }

    @Override
    public Long insert(RoleDO roleDO) {
        roleMapper.insert(roleDO);
        return roleDO.getId();
    }

    @Override
    public boolean updateByPrimaryKey(Long id, RoleDO roleDO) {
        roleDO.setId(id);
        return roleMapper.updateById(roleDO) == 1;
    }

    public List<RoleDO> selectBatchIds(Collection<? extends Serializable> ids) {
        return roleMapper.selectBatchIds(ids);
    }
}
