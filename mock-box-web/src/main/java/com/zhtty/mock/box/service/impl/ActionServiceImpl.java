package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.model.out.upms.ActionResponse;
import com.zhtty.mock.box.repository.ActionRepository;
import com.zhtty.mock.box.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<ActionResponse> getActionsByIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids))
            return Collections.emptyList();
        return actionRepository.selectBatchIds(ids)
                .stream()
                .map(actionDO -> ActionResponse.builder()
                        .action(actionDO.getAction())
                        .defaultCheck(actionDO.getDefaultCheck())
                        .describe(actionDO.getDescribe())
                        .build())
                .collect(Collectors.toList());
    }
}
