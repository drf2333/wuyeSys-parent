package com.drf.wy.web.system.service;

import com.drf.wy.web.system.entity.SaveRoleIdsByUserIdParam;
import com.drf.wy.web.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色中间表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    Boolean saveRoleIdsByUserId(SaveRoleIdsByUserIdParam param);

}
