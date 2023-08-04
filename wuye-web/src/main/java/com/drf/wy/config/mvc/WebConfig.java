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

/*将自定义的JwtAuthenticationTokenFilter过滤器注册到Servlet容器中。*/
public class WebConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(JwtAuthenticationTokenFilter
                                                                     jwtAuthenticationTokenFilter){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(jwtAuthenticationTokenFilter);

        //false表示不启用该过滤器
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;

    }
}
