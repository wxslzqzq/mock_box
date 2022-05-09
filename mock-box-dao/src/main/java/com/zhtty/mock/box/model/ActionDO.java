package com.zhtty.mock.box.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.zhtty.mock.box.model.BaseDO;

/**
 * 用户行为表
 *
 * @author AutoGenerator
 * @since 2022-05-09
 */
@TableName("t_mock_upms_action")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionDO extends BaseDO {


    /**
     * checkable
     */
    private Boolean defaultCheck;
    /**
     * 行为名称/行为描述
     */
    @TableField(value = "`describe`")
    private String describe;
    /**
     * 行为动作
     */
    @TableField(value = "`action`")
    private String action;
}
