package com.zhtty.mock.box.service;


import com.zhtty.mock.box.model.out.upms.ActionResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
public interface ActionService {

    List<ActionResponse> getActionsByIds(List<Long> id);
}
