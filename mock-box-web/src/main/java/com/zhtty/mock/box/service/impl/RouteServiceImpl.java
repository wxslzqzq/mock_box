package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.model.RouteDO;
import com.zhtty.mock.box.model.out.MetaResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;
import com.zhtty.mock.box.repository.RouteRepository;
import com.zhtty.mock.box.repository.UserRepository;
import com.zhtty.mock.box.service.RoleService;
import com.zhtty.mock.box.service.RouteService;
import com.zhtty.mock.box.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author AutoGenerator
 * @since 2022-05-02
 */
@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    /**
     * 1.根据角色id获取所有路由routeDOS
     *
     * @param ids
     * @return
     */
    @Override
    public List<RoutesResponse> getRoutesByRoleIds(List<Long> ids) {
        Set<RoleDO> roles = roleService.getRolesByIds(ids);
        Set<Long> routeIds = new HashSet<>(10);
        roles.forEach(roleDO -> routeIds.addAll(Objects.requireNonNull(JsonUtils.readValue(new TypeReference<List<Long>>() {
        }, roleDO.getRoutes()))));
        List<RouteDO> routeDOS = routeRepository.selectBatchIds(routeIds);//取出本用户下的所有路由
        return routeDOS.stream().map(routeDO -> RoutesResponse.builder()
                .id(routeDO.getId())
                .hidden(routeDO.getHidden())
                .path(routeDO.getPath())
                .component(routeDO.getComponent())
                .redirect(routeDO.getRedirect())
                .name(routeDO.getName())
                .parentId(routeDO.getMasterId())//parentId前端增加容错排序需要
                .meta(JsonUtils.readValue(new TypeReference<MetaResponse>() {
                }, routeDO.getMeta()))
                .hideChildrenInMenu(routeDO.getHideChildrenInMenu())
                .build()).collect(Collectors.toList());

    }

}
