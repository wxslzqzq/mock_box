package com.zhtty.mock.box.model.out.upms;

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
public class MetaResponse {
    private String title;
    /**
     * 设置该路由的图标，支持 svg-class，也支持 el-icon-x element-ui 的 icon
     */
    private String icon;
    /**
     * ?
     */
    private Boolean show;
    /**
     * _blank表示打开到新窗口
     */
    private String target;
    /**
     * 特殊 隐藏 PageHeader 组件中的页面带的 面包屑和页面标题栏
     */
    private Boolean hiddenHeaderContent;

    private List<String> permission;

    private Boolean hideHeader;
    private Boolean hideChildren;

}
