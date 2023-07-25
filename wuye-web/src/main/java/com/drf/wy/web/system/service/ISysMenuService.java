package com.drf.wy.web.system.service;

import com.drf.wy.web.system.entity.CheckMenuTreeVo;
import com.drf.wy.web.system.entity.SaveMenuIdsByRoleIdParam;
import com.drf.wy.web.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
public interface ISysMenuService extends IService<SysMenu> {
    List getList();

    List<SysMenu> getParentList();
    CheckMenuTreeVo getAssignTree(Integer roleId);

    void saveMenuIdsByRoleId(SaveMenuIdsByRoleIdParam param);

}
