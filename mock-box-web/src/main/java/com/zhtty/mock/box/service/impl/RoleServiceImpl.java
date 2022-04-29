package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.repository.RoleRepository;
import com.zhtty.mock.box.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<RoleDO> getRolesByIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptySet();
        }
        List<RoleDO> roleDOS = roleRepository.selectBatchIds(ids);
        return new HashSet<>(roleDOS);
    }
}
