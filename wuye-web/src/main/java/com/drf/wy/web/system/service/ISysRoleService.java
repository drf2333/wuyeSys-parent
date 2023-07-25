package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.SaveRoleIdsByUserIdParam;
import com.drf.wy.web.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.SysRoleParam;
import com.drf.wy.web.system.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRole> list(SysRoleParam sysRoleParam);

    List<SysRole> checkRoles(Integer userId);


}
