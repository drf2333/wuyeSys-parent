package com.drf.wy.web.system.mapper;

import com.drf.wy.web.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色菜单中间表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


    List<Integer> selectMenuIdsByRoleId(Integer roleId);
}
