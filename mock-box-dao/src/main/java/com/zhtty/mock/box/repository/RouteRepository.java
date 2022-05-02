package com.zhtty.mock.box.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhtty.mock.box.QueryWrapperBuilder;
import com.zhtty.mock.box.dao.mapper.RouteMapper;
import com.zhtty.mock.box.model.RouteDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class RouteRepository implements IRepository<Long, RouteDO> {
    @Autowired
    private RouteMapper mapper;

    @Override
    public Optional<RouteDO> selectById(Long aLong) {
        return Optional.ofNullable(mapper.selectById(aLong));
    }

    public List<RouteDO> selectBatchIds(Collection<? extends Serializable> ids) {
        return mapper.selectBatchIds(ids);
    }

    public Optional<RouteDO> selectMasterById(Long aLong) {
        QueryWrapper<RouteDO> queryWrapper = QueryWrapperBuilder.build();
        queryWrapper.lambda().eq(RouteDO::getMasterId, aLong);
        queryWrapper.lambda().eq(RouteDO::getId, aLong);
        return Optional.ofNullable(mapper.selectOne(queryWrapper));
    }

    public List<RouteDO> selectByMasterId(Long aLong) {
        QueryWrapper<RouteDO> queryWrapper = QueryWrapperBuilder.build();
        queryWrapper.lambda().eq(RouteDO::getMasterId, aLong);
        List<RouteDO> routeDOS = mapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(routeDOS))
            return Collections.emptyList();
        return routeDOS;
    }

    @Override
    public Long insert(RouteDO routeDO) {
        mapper.insert(routeDO);
        return routeDO.getId();
    }

    @Override
    public boolean updateByPrimaryKey(Long id, RouteDO routeDO) {
        routeDO.setId(id);
        return mapper.updateById(routeDO) == 1;
    }
}
