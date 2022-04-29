package com.zhtty.mock.box.service;


import com.zhtty.mock.box.model.RoleDO;

import java.util.List;
import java.util.Set;

/**
 * 服务类
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
public interface RoleService {

    Set<RoleDO> getRolesByIds(List<Long> ids);
}
