package com.demo.common.config;

import com.xsy.base.config.FieldMetaObjectHandler;
import com.xsy.security.user.SecurityUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.4.25 15:30
 */
@Component
public class MyFieldMetaObjectHandler extends FieldMetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        super.insertFill(metaObject);
        strictInsertFill(metaObject, "userId", Long.class, SecurityUser.getUserId());

    }
}
