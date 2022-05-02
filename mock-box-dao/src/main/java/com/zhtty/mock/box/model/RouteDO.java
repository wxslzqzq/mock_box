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
@TableName("t_mock_upms_route")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDO extends BaseDO {
    /**
     * 上级路由ID,若为1级路由则为自己
     */
    private Long masterId;
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
     * 默认1:true表示显示根路由
     */
    private Boolean alwaysShow;
    /**
     * 路由名称，非空
     */
    private String name;
    /**
     * 元数据id
     */
    private Long meta;
    /**
     * 高亮配置
     */
    private String activeMenu;
}
