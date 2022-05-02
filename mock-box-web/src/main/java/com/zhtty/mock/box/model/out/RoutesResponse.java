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
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class RoutesResponse {
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
     * 元数据
     */
    private MetaResponse meta;
    ;
    /**
     * 高亮配置
     */
    private String activeMenu;

    private List<RoutesResponse> children;
}
