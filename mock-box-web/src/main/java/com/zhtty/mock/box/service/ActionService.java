package com.zhtty.mock.box.service;


import com.zhtty.mock.box.model.ActionDO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 服务类
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
public interface ActionService {
    List<ActionDO> getActionsByIds(Collection<? extends Serializable> ids);


}
