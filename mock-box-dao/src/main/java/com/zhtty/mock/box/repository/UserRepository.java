package com.zhtty.mock.box.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhtty.mock.box.QueryWrapperBuilder;
import com.zhtty.mock.box.mapper.UserMapper;
import com.zhtty.mock.box.model.UserDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class UserRepository implements IRepository<Long, UserDO> {
    @Resource
    private UserMapper userMapper;

    @Override
    public Optional<UserDO> selectById(Long aLong) {
        return Optional.ofNullable(userMapper.selectById(aLong));
    }

    @Override
    public Long insert(UserDO userDO) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKey(Long id, UserDO userDO) {
        return false;
    }

    public Optional<UserDO> selectOneByUserNo(String userNo) {
        QueryWrapper<UserDO> queryWrapper = QueryWrapperBuilder.build();
        if (StrUtil.isNotBlank(userNo)) {
            queryWrapper.lambda().like(UserDO::getUserNo, userNo);
        }
        return Optional.ofNullable(userMapper.selectOne(queryWrapper));
    }
}
