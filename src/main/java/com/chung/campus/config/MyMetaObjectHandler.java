package com.chung.campus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("sql insert");
        //this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
        //this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("sql update");
        //this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.strictInsertFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now());
    }
}