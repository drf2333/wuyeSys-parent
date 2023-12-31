package com.drf.wy.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 17:42
 * @Project_Name:wuyeSys-parent
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //注入DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        System.out.println(passwordEncoder().encode("123456"));
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    //注入认证管理器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //注入过滤链
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //关闭csrf()攻击防护
        http.csrf().disable();
        //允许跨域,允许嵌入 iframe 窗口
        http.cors();
        //关闭iframe窗口防护
        http.headers().frameOptions().disable();
        //关闭session会话
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            //配置认证过滤器
            http.addFilterAfter(
                    jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class
            );
            //配置所有请求必须认证
            http.authorizeRequests().anyRequest().authenticated();

            //认证失败处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);


        return http.build();
    }


    //配置忽略路径
    @Bean
    public WebSecurityCustomizer securityCustomizer() throws Exception {
        return (web) -> {
            web.ignoring().antMatchers(
                    "/api/captcha",
                    "/api/login",
                    "/doc.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v2/api-docs/**");
        };
    }
}
