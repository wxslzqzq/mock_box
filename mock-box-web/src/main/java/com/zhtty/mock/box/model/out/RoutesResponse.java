package com.zhtty.mock.box.model.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RoutesResponse {

    private Long id;
    /**
     * 上级路由ID,若为1级路由则为自己
     */
    private Long parentId;
    /**
     * 路由
     */
    private String path;
    /**
     * 组件名称
     */
    private String component;
    /**
     * 默认0:false,表示路由在侧边栏不会显示，用于页面内按钮展示等控制
     */
    private Boolean hidden;
    /**
     * 默认为noRedirect，表示在面包屑导航中不可被点击
     */
    private String redirect;
    /**
     * 强制菜单显示为Item而不是SubItem(配合 meta.hidden)
     */
    private Boolean hideChildrenInMenu;
    /**
     * 路由名称, 必须设置,且不能重名
     */
    private String name;
    /**
     * 路由元信息（路由附带扩展信息）
     */
    private MetaResponse meta;
}
