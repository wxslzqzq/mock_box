package com.zhtty.mock.box.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.RoleDO;
import com.zhtty.mock.box.model.RouteDO;
import com.zhtty.mock.box.model.RouteMetaDO;
import com.zhtty.mock.box.model.out.MetaResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;
import com.zhtty.mock.box.repository.RouteMetaRepository;
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
    private RouteMetaRepository routeMetaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    /**
     * 1.根据角色id获取所有路由routeDOS
     * 2.遍历routeDOS，获取master路由和sub路由
     * 3.组装children
     * 4.组装List<RoutesResponse>
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
        List<RouteDO> masterRoutes = routeDOS.stream().filter(routeDO -> Objects.equals(routeDO.getId(), routeDO.getMasterId())).collect(Collectors.toList());
        routeDOS.removeAll(masterRoutes);//取差集，剩下的都是二级菜单
        return masterRoutes.stream().map(masterRoute -> {
            RouteMetaDO meta = routeMetaRepository.selectById(masterRoute.getMeta()).orElseThrow(() -> new BizException(ExceptionMessageEnum.ROUTES_LOAD_FAIL));
            List<Long> metaRoleIds = JsonUtils.readValue(new TypeReference<List<Long>>() {
            }, meta.getRoles());
            Set<RoleDO> metaRoles = roleService.getRolesByIds(metaRoleIds);
            List<RouteDO> subRoutes = routeDOS.stream()
                    .filter(subRoute -> Objects.equals(subRoute.getMasterId(), masterRoute.getId()))//筛选出二级菜单
                    .collect(Collectors.toList());
            List<RoutesResponse> children = getChildren(meta, metaRoles, subRoutes);
            if (CollectionUtil.isEmpty(children)) children = null;//空集合会影响序列化时的忽略
            return RoutesResponse.builder()
                    .activeMenu(masterRoute.getActiveMenu())
                    .hidden(masterRoute.getHidden())
                    .path(masterRoute.getPath())
                    .component(masterRoute.getComponent())
                    .redirect(masterRoute.getRedirect())
                    .alwaysShow(masterRoute.getAlwaysShow())
                    .name(masterRoute.getName())
                    .meta(MetaResponse.builder()
                            .affix(meta.getAffix())
                            .breadcrumb(meta.getBreadcrumb())
                            .icon(meta.getIcon())
                            .activeMenu(meta.getActiveMenu())
                            .roles(JsonUtils.writeValueAsString(metaRoles.stream()
                                    .map(RoleDO::getRoleName)
                                    .collect(Collectors.toList())))
                            .noCache(meta.getNoCache())
                            .title(meta.getTitle())
                            .build())
                    .children(children)
                    .build();
        }).collect(Collectors.toList());

    }

    private List<RoutesResponse> getChildren(RouteMetaDO meta, Set<RoleDO> metaRoles, List<RouteDO> subRoutes) {
        if (CollectionUtil.isEmpty(subRoutes))
            return Collections.emptyList();
        return subRoutes.stream()
                .map(child -> RoutesResponse.builder()
                        .activeMenu(child.getActiveMenu())
                        .hidden(child.getHidden())
                        .path(child.getPath())
                        .component(child.getComponent())
                        .redirect(child.getRedirect())
                        .alwaysShow(child.getAlwaysShow())
                        .name(child.getName())
                        .meta(MetaResponse.builder()
                                .affix(meta.getAffix())
                                .breadcrumb(meta.getBreadcrumb())
                                .icon(meta.getIcon())
                                .activeMenu(meta.getActiveMenu())
                                .roles(JsonUtils.writeValueAsString(metaRoles.stream()
                                        .map(RoleDO::getRoleName)
                                        .collect(Collectors.toList())))
                                .noCache(meta.getNoCache())
                                .title(meta.getTitle())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

}
