package com.drf.wy.config.security;


import com.drf.wy.utils.JwtUtils;
import com.drf.wy.utils.RedisConstant;
import com.drf.wy.utils.SystemConstant;
import com.drf.wy.web.system.entity.LiveUser;
import com.drf.wy.web.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 19:58
 * @Project_Name:wuyeSys-parent
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
      //  String token = request.getHeader("token");
        String token = request.getHeader("authorization");

        if (!StringUtils.hasText(token)){
             token = request.getParameter("authorization");
        }
        if (!StringUtils.hasText(token)) {
            //token 为空直接放行
            filterChain.doFilter(request, response);
            //防止过滤连执行完在执行过滤器
            return;
        }
        //解析token

        Integer userId;
        Integer userType;

        try {
            userType= jwtUtils.getUserTypeFromToken(token);
            userId= jwtUtils.getUserIdFromToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token 非法");
        }
        //刷新token和redis有效期

        String refreshToken = jwtUtils.refreshToken(token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", refreshToken);


        //判断用户类型，取出对应用户信息,刷新缓存
        if (SystemConstant.USER_TYPE_WUZHU == userType) {
            System.out.println("usertype ==="+userType);
            System.out.println("redis的名字"+RedisConstant.LOGIN_SYSTEM_USER_PRE + userId);
            SysUser sysUser = (SysUser) redisTemplate.opsForValue().get(RedisConstant.LOGIN_SYSTEM_USER_PRE + userId);
            if (Objects.isNull(sysUser)) {
                throw new RuntimeException("用户未登录");
            }
            redisTemplate.expire(RedisConstant.LOGIN_SYSTEM_USER_PRE + userId, RedisConstant.LOGIN_SYSTEM_USER_EXPIRE_TIME, TimeUnit.MINUTES);

            //将用户信息存入SecurityContextHolder中
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(sysUser, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //放行
            filterChain.doFilter(request, response);
        } else
        {
            LiveUser liveUser = (LiveUser) redisTemplate.opsForValue().get(RedisConstant.LOGIN_LIVE_USER_PRE + userId);
            if (Objects.isNull(liveUser)) {
                throw new RuntimeException("用户未登录");
            }
            redisTemplate.expire(RedisConstant.LOGIN_LIVE_USER_PRE + userId, RedisConstant.LOGIN_LIVE_USER_EXPIRE_TIME, TimeUnit.MINUTES);

            //将用户信息存入SecurityContextHolder中
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(liveUser, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //放行
            filterChain.doFilter(request, response);
        }
    }
}
