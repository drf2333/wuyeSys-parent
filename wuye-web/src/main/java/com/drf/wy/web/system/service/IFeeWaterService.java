package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.FeeWater;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.FeeWaterParam;

/**
 * <p>
 * 水费表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface IFeeWaterService extends IService<FeeWater> {
    IPage<FeeWater> getList(FeeWaterParam param);

    void saveFeeWater(FeeWater feeWater);

    void updateFeeWater(FeeWater feeWater);
}
