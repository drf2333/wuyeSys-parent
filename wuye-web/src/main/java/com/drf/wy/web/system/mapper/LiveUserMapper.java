package com.drf.wy.web.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.LiveUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 业主表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-21
 */
public interface LiveUserMapper extends BaseMapper<LiveUser> {


    IPage<LiveUser> getList(IPage<LiveUser> page, @Param("trueName") String trueName, @Param("phone") String phone);

    LiveUser getUser(@Param("userId") Integer userId);
}
