package com.drf.wy.web.system.service.impl;

import com.drf.wy.utils.JwtUtils;
import com.drf.wy.utils.RedisConstant;
import com.drf.wy.utils.Result;
import com.drf.wy.utils.SystemConstant;
import com.drf.wy.web.system.entity.LiveUser;
import com.drf.wy.web.system.entity.LoginParam;
import com.drf.wy.web.system.entity.SysUser;
import com.drf.wy.web.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 16:32
 * @Project_Name:wuyeSys-parent
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public Result login(LoginParam loginParam) {
        //1.校验验证码是否正确
        validCaptcha(loginParam.getCaptchaId(), loginParam.getCaptchaCode());
        //2.校验用户名密码是否正确
        Authentication authentication = validUsernameAndPassword(loginParam.getUsername(), loginParam.getUserType(), loginParam.getPassword());
        //3.用户信息存入redis，并响应token数据
        System.out.println("username=="+loginParam.getUsername());
        System.out.println("userType=="+loginParam.getUserType());
        System.out.println("password=="+loginParam.getPassword());
        System.out.println("CaptchaCode=="+loginParam.getCaptchaCode());
        System.out.println("CaptchaId=="+loginParam.getCaptchaId());
        return responseToken(loginParam.getUserType(), authentication);
    }

    @Override
    public Result logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof LiveUser) {
            LiveUser liveUser = (LiveUser) principal;
            redisTemplate.delete(RedisConstant.LOGIN_LIVE_USER_PRE + liveUser.getUserId());
        } else if (principal instanceof SysUser) {
            SysUser sysUser = (SysUser) principal;
            redisTemplate.delete(RedisConstant.LOGIN_SYSTEM_USER_PRE + sysUser.getUserId());
        }
        return Result.success();
    }


    //校验验证码的private  方法 封装起来
    private void validCaptcha(String captchaId, String captchaCode) {
        //获取redis中的验证码
        String captchaCode2 = (String) redisTemplate.opsForValue().get(RedisConstant.CAPTCHA_PRE + captchaId);
        if (!captchaCode.equalsIgnoreCase(captchaCode2)) {
            throw new RuntimeException("验证码错误");
        }
    }

    //校验用户名和密码
    private Authentication validUsernameAndPassword(String username, Integer userType, String password) {
        username = username + ":" + userType;
        //将用户名和密码封装成token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        //进行认证
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        return authenticate;
    }

    //响应token
    private Result responseToken(Integer userType, Authentication authentication) {
        Integer userId;
        String username;
        //如果登录的用户是业主，将业主的信息存入redis，并且响应token数据
        if (userType == SystemConstant.USER_TYPE_YEZHU) {//sys:1 live:0 此处判断YEZHU 0
            //获取业主信息
            LiveUser liveUser = (LiveUser) authentication.getPrincipal();
            userId = liveUser.getUserId();
            username = liveUser.getUsername();
            //将业主信息存入redis
            redisTemplate.opsForValue().set(
                    RedisConstant.LOGIN_LIVE_USER_PRE + userId,
                    liveUser,
                    RedisConstant.LOGIN_LIVE_USER_EXPIRE_TIME,
                    TimeUnit.MINUTES);


        } else {
            //获取用户信息
            SysUser sysUser = (SysUser) authentication.getPrincipal();
            userId = sysUser.getUserId();
            username = sysUser.getUsername();
            //用户信息存入redis
            redisTemplate.opsForValue().set(
                    RedisConstant.LOGIN_SYSTEM_USER_PRE + userId,
                    sysUser,
                    RedisConstant.LOGIN_SYSTEM_USER_EXPIRE_TIME,
                    TimeUnit.MINUTES
            );
        }
        //把token 相应给前端
        String token = jwtUtils.generateToken(userId, username, userType);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
       // System.out.println("登录====="+map);
        return Result.success(map);
    }

}
