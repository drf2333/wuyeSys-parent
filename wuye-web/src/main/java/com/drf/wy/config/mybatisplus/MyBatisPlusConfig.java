package com.drf.wy.config.mybatisplus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 10:21
 * @Project_Name:wuyeSys-parent  开启扫描、分页功能
 */
@Configuration
@MapperScan("com.drf.wy.web.*.mapper")//用于指定MyBatis的Mapper接口所在的包路径，
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        /*向MybatisPlusInterceptor对象中添加一个分页插件，
        该插件是MyBatis Plus提供的一个内部拦截器，用于实现分页功能。在这里使用的数据库类型是MySQL。*/
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;

    }
}
