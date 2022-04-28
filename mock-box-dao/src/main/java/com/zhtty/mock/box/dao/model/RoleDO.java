package com.zhtty.mock.box.dao.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.zhtty.mock.box.model.BaseDO;

/**
 * 用户行为表
 *
 * @author AutoGenerator
 * @since 2022-04-28
 */
@TableName("t_mock_upms_role")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDO extends BaseDO {


    /**
     * 所属角色
     */
    private Long masterId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色组集合
     */
    private String permissionGroupIdList;
}
