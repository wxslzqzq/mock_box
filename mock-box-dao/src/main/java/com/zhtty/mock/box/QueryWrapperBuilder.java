package com.zhtty.mock.box;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author A8142
 */
public class QueryWrapperBuilder {

    public static <T> QueryWrapper<T> build() {
        return new QueryWrapper<>();
    }
}