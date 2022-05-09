package com.zhtty.mock.box.service;


import com.zhtty.mock.box.model.out.upms.RoutesResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author AutoGenerator
 * @since 2022-05-02
 */
public interface RouteService {
    List<RoutesResponse> getRoutesByRoleIds(List<Long> id);
}
