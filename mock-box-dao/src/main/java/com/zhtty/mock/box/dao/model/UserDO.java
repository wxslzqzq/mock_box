package com.zhtty.mock.box.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_mock_upms_user")
public class UserDO extends BaseDO {
    private String userNo;
    private String password;//加salt后的值
    private String userName;
    private String roleIdList;
}
