package com.zhtty.mock.box.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.dao.mapper.ActionMapper;
import com.zhtty.mock.box.model.ActionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ActionRepository implements IRepository<Long, ActionDO> {
    @Autowired
    private ActionMapper mapper;

    @Override
    public Optional<ActionDO> selectById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Long insert(ActionDO actionDO) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKey(Long id, ActionDO actionDO) {
        return false;
    }

    public List<ActionDO> selectBatchIds(Collection<? extends Serializable> ids) {
        if (CollectionUtil.isEmpty(ids))
            return Collections.emptyList();
        return mapper.selectBatchIds(ids);
    }
}
