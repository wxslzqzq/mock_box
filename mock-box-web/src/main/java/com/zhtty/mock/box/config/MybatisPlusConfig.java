package com.zhtty.mock.box.config;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("com.zhtty.mock.box.dao.mapper")
public class MybatisPlusConfig {

    private final static String IS_DELETED = "is_deleted";

    private final static String GMT_CREATE = "gmtCreate";

    private final static String GMT_MODIFIED = "gmtModified";

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MetaObjectHandler autoFillMetaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                if (metaObject.hasGetter(IS_DELETED)) {
                    this.setFieldValByName(IS_DELETED, 0, metaObject);
                }
                if (metaObject.hasGetter(GMT_CREATE) && metaObject.getValue(GMT_CREATE) == null) {
                    this.setFieldValByName(GMT_CREATE, DateUtil.date(), metaObject);
                }
                if (metaObject.hasGetter(GMT_MODIFIED) && metaObject.getValue(GMT_MODIFIED) == null) {
                    this.setFieldValByName(GMT_MODIFIED, DateUtil.date(), metaObject);
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                if (metaObject.hasGetter(GMT_MODIFIED) && metaObject.getValue(GMT_MODIFIED) == null) {
                    this.setFieldValByName(GMT_MODIFIED, DateUtil.date(), metaObject);
                }
            }
        };
    }

}