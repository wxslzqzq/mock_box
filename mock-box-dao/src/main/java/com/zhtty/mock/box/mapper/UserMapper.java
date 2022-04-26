package com.zhtty.mock.box.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhtty.mock.box.model.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}