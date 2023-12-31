package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drf.wy.web.system.entity.SaveRoleIdsByUserIdParam;
import com.drf.wy.web.system.entity.SysRole;
import com.drf.wy.web.system.entity.SysRoleParam;
import com.drf.wy.web.system.entity.SysUserRole;
import com.drf.wy.web.system.mapper.SysRoleMapper;
import com.drf.wy.web.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper userRoleMapper;

    @Override
    public IPage<SysRole> list(SysRoleParam sysRoleParam) {
        //构建分页对象
        IPage<SysRole> page = new Page<>(sysRoleParam.getCurrentPage(),sysRoleParam.getPageSize());
        //构建查询条件
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(sysRoleParam.getRoleName()))
        {
            queryWrapper.like(SysRole::getRoleName,sysRoleParam.getRoleName());
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysRole> checkRoles(Integer userId) {
        //查询所有角色信息
       List<SysRole> roles = this.baseMapper.selectList(null);
       List<Integer> sysUserRoles = userRoleMapper.selectRoleIds(userId);
       List<SysRole> result = roles.stream().map(role->{
            if(sysUserRoles.contains(role.getRoleId())){
                role.setChecked(true);
            }
            return role;
        }).collect(Collectors.toList());
        return result;
    }


}
