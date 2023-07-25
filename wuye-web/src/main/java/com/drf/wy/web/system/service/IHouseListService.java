package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.HouseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.HouseListParam;

/**
 * <p>
 * 房屋表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface IHouseListService extends IService<HouseList> {

    IPage<HouseList> getList(HouseListParam param);

}
