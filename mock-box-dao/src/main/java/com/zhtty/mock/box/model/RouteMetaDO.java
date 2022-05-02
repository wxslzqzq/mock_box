package com.zhtty.mock.box.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.zhtty.mock.box.model.BaseDO;

/**
 * 路由信息表
 *
 * @author AutoGenerator
 * @since 2022-05-02
 */
@TableName("t_mock_upms_route_meta")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteMetaDO extends BaseDO {


    /**
     * 角色
     */
    private String roles;
    /**
     * 路由名称，非空
     */
    private String title;
    /**
     * 设置该路由的图标，支持 svg-class，也支持 el-icon-x element-ui 的 icon
     */
    private String icon;
    /**
     * 路由名称，非空
     */
    private Boolean noCache;
    /**
     * 如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
     */
    private Boolean breadcrumb;
    /**
     * 如果设置为true，它则会固定在tags-view中(默认 false)
     */
    private Boolean affix;
    /**
     * 高亮相对应的侧边栏
     */
    private String activeMenu;
}
