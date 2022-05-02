package com.zhtty.mock.box.model;


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
     * 角色名称
     */
    private String roleName;
    /**
     * 中文别名
     */
    private String alias;
    /**
     * 角色组集合
     */
    private String routes;
}
