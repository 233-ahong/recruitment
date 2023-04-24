package com.ah.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author 26471
 * @date 2023/4/19 21:35
 * @description mybatis-plus 自动填充创建/更新时间
 */
public class DateHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        // 自动填充创建时间
        this.setFieldValByName("createTime", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        // 自动填充更新时间
        this.setFieldValByName("updateTime", now, metaObject);
    }
}

