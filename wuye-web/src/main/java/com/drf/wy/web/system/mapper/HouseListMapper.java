package com.drf.wy.web.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.HouseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 房屋表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface HouseListMapper extends BaseMapper<HouseList> {

    IPage<HouseList> getList(
            IPage<HouseList> page,
            @Param("buildName") String buildName,
            @Param("unitName") String unitName,
            @Param("houseNum") String houseNum,
            @Param("status") Integer status);
}
