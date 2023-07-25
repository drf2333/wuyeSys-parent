package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.HouseUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.HouseUnitParam;

/**
 * <p>
 * 单元表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface IHouseUnitService extends IService<HouseUnit> {

    IPage<HouseUnit> getList(HouseUnitParam param);
}
