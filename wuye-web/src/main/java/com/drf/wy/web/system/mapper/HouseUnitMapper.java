package com.drf.wy.web.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.HouseUnit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drf.wy.web.system.entity.HouseUnitParam;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 单元表 Mapper 接口
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface HouseUnitMapper extends BaseMapper<HouseUnit> {

    IPage<HouseUnit> getList(IPage<HouseUnit> page, @Param("buildName") String buildName, @Param("unitName") String unitName);

}
