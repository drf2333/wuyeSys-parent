package com.drf.wy.config.security;


import cn.hutool.json.JSONUtil;
import com.drf.wy.utils.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/22 23:04
 * @Project_Name:wuyeSys-parent  认证异常处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    //在认证失败时，返回给客户端一个未授权的JSON响应结果，以提醒客户端认证失败。
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Result result = Result.unauthorized();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSONUtil.toJsonStr(result));


    }
}
