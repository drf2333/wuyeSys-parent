package com.drf.wy.web.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drf.wy.utils.SystemConstant;
import com.drf.wy.web.system.entity.LiveUser;
import com.drf.wy.web.system.entity.SysUser;
import com.drf.wy.web.system.mapper.LiveUserMapper;
import com.drf.wy.web.system.mapper.SysUserMapper;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 18:55
 * @Project_Name:wuyeSys-parent
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LiveUserMapper liveUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //接收到到username是 test:1形式 1代表物主身份，0代表业主身份获取用户身份
        Integer userType = Integer.parseInt(username.substring(username.indexOf(":") + 1));
        //获取用户名
        username = username.substring(0,username.indexOf(":"));
        //物主身份查询
        if (userType == SystemConstant.USER_TYPE_WUZHU) {
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.lambda().eq(SysUser::getUsername, username);
            SysUser sysUser = sysUserMapper.selectOne(query);

            if (Objects.isNull(sysUser)) {
                throw new RuntimeException("用户名错误");
            }
            return sysUser;


        } else if (userType == SystemConstant.USER_TYPE_YEZHU) {
            QueryWrapper<LiveUser> query = new QueryWrapper<>();
            query.lambda().eq(LiveUser::getUsername, username);
            LiveUser liveUser = liveUserMapper.selectOne(query);
            if (Objects.isNull(liveUser)){
                throw new RuntimeException("用户名错误");
            }
            return liveUser;


        }else{
            return null;
        }


    }
}
