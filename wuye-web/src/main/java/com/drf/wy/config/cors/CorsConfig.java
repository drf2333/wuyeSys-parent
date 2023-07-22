package com.drf.wy.config.cors;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 14:43
 * @Project_Name:wuyeSys-parent
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter (){

        CorsConfiguration config = new CorsConfiguration();
        //接受任意域名请求
        config.addAllowedOrigin("*");
        //不支持提交cookie数据
        config.setAllowCredentials(false);
        //绑定请求头信息，使用通配符*接受任意字段
        config.addAllowedHeader("*");
        //支持任意提交方法
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(configSource);

    }
}
