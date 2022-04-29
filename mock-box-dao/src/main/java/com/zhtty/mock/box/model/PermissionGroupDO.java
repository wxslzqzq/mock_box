package com.zhtty.mock.box.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.zhtty.mock.box.model.BaseDO;

/**
 * 用户权限组登记表
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
@TableName("t_mock_upms_permission_group")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionGroupDO extends BaseDO {


    /**
     * 权限名称
     */
    private String permissionGroupName;
    /**
     * 中文别名
     */
    private String alias;
    /**
     * 权限组行为集合
     */
    private String actionIdList;
}
