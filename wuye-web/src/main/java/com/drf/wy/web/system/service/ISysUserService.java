package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.SysUserParam;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-21
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUser> list(SysUserParam sysUserParam);
}
