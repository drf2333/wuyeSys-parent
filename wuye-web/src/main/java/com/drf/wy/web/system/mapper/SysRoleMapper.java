package com.drf.wy.web.system.mapper;

import com.drf.wy.web.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<Integer> selectRoleIds(Integer userId);

}
