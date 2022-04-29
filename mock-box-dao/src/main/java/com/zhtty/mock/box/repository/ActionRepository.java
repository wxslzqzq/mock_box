package com.zhtty.mock.box.repository;

import com.zhtty.mock.box.dao.mapper.ActionMapper;
import com.zhtty.mock.box.model.ActionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class ActionRepository implements IRepository<Long, ActionDO> {
    @Autowired
    private ActionMapper actionMapper;

    @Override
    public Optional<ActionDO> selectById(Long aLong) {
        return Optional.of(actionMapper.selectById(aLong));
    }

    @Override
    public Long insert(ActionDO actionDO) {
        actionMapper.insert(actionDO);
        return actionDO.getId();
    }

    @Override
    public boolean updateByPrimaryKey(Long id, ActionDO actionDO) {
        actionDO.setId(id);
        return actionMapper.updateById(actionDO) == 1;
    }

    public List<ActionDO> selectBatchIds(Collection<? extends Serializable> ids) {
        return actionMapper.selectBatchIds(ids);
    }
}
