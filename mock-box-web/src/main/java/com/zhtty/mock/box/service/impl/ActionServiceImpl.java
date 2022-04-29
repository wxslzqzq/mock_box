package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.model.ActionDO;
import com.zhtty.mock.box.repository.ActionRepository;
import com.zhtty.mock.box.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<ActionDO> getActionsByIds(Collection<? extends Serializable> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return actionRepository.selectBatchIds(ids);
    }
}
