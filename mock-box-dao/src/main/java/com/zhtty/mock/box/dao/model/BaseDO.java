package com.zhtty.mock.box.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author A8142
 */
@Data
public class BaseDO implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private Date gmtModified;

    protected static final long serialVersionUID = 1L;
}