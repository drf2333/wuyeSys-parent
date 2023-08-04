package com.drf.wy.web.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.FeeWater;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 水费表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface FeeWaterMapper extends BaseMapper<FeeWater> {
    IPage<FeeWater> getList(
            IPage<FeeWater> page,
            @Param("username") String username,
            @Param("houseNum") String houseNum);
}
