package com.zhtty.mock.box.repository;

import com.zhtty.mock.box.dao.mapper.RouteMetaMapper;
import com.zhtty.mock.box.model.RouteMetaDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RouteMetaRepository implements IRepository<Long, RouteMetaDO> {
    @Autowired
    private RouteMetaMapper mapper;

    @Override
    public Optional<RouteMetaDO> selectById(Long aLong) {
        return Optional.ofNullable(mapper.selectById(aLong));
    }

    @Override
    public Long insert(RouteMetaDO routeMetaDO) {
        mapper.insert(routeMetaDO);
        return routeMetaDO.getId();
    }

    @Override
    public boolean updateByPrimaryKey(Long id, RouteMetaDO routeMetaDO) {
        routeMetaDO.setId(id);
        return mapper.updateById(routeMetaDO) == 1;
    }
}
