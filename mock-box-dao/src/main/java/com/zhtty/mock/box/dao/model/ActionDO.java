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
@TableName("t_mock_upms_action")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionDO extends BaseDO {


    /**
     * 所属权限组
     */
    private Long masterId;
    /**
     * 行为名称
     */
    private String actionName;
    /**
     * 行为动作
     */
    private String action;
}
