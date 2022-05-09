package com.zhtty.mock.box.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.zhtty.mock.box.model.BaseDO;

/**
 * 用户权限表
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
@TableName("t_mock_upms_permission")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDO extends BaseDO {


    /**
     * 权限中文名称
     */
    private String permissionName;
    /**
     * 权限英文名称
     */
    private String permissionId;
    /**
     * 行为集合
     */
    private String actions;
}
