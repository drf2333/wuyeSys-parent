package com.drf.wy.config.mybatisplus;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 10:25
 * @Project_Name:wuyeSys-parent
 */
@Repository
@Slf4j
public class MyBatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ...");
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
