package com.zhtty.mock.box.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO {
    private String userNo;
    private String password;//加salt后的值
    private String userName;
    private String roleIdList;
}
