package com.drf.wy.config.mvc;


import com.drf.wy.config.security.JwtAuthenticationTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 20:26
 * @Project_Name:wuyeSys-parent
 */
@Configuration

public class WebConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(JwtAuthenticationTokenFilter
                                                                     jwtAuthenticationTokenFilter){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(jwtAuthenticationTokenFilter);

        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;

    }
}
