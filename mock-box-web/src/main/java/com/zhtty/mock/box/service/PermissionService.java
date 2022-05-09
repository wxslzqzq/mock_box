package com.zhtty.mock.box.service;


import com.zhtty.mock.box.model.PermissionDO;
import com.zhtty.mock.box.model.RoleDO;

import java.util.List;
import java.util.Set;

/**
 * 服务类
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
public interface PermissionService  {

    Set<PermissionDO> getPermissionsByIds(Set<RoleDO> sets);

}
