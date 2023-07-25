package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.drf.wy.web.system.entity.SaveRoleIdsByUserIdParam;
import com.drf.wy.web.system.entity.SysUserRole;
import com.drf.wy.web.system.mapper.SysUserRoleMapper;
import com.drf.wy.web.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户角色中间表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Override
    public Boolean saveRoleIdsByUserId(SaveRoleIdsByUserIdParam param) {
        //清空用户起初拥有的角色id关联
        LambdaQueryWrapper<SysUserRole> query = new LambdaQueryWrapper<>();
        query.eq(SysUserRole::getUserId,param.getUserId());
        this.baseMapper.delete(query);
        //保存为用户新分配的角色信息
        List<Integer> roleIds = param.getRoleIds();
        if(!Objects.isNull(roleIds) && roleIds.size()>0){
            for(Integer roleId : roleIds){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(param.getUserId());
                sysUserRole.setRoleId(roleId);
                this.baseMapper.insert(sysUserRole);
            }
        }
        return true;
    }
}
