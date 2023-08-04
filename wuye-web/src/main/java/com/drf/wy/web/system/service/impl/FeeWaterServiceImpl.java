package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drf.wy.web.system.entity.FeeWater;
import com.drf.wy.web.system.entity.FeeWaterParam;
import com.drf.wy.web.system.mapper.FeeWaterMapper;
import com.drf.wy.web.system.service.IFeeWaterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 水费表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@Service
public class FeeWaterServiceImpl extends ServiceImpl<FeeWaterMapper, FeeWater> implements IFeeWaterService {

    @Override
    public IPage<FeeWater> getList(FeeWaterParam param) {
        IPage<FeeWater> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        return this.baseMapper.getList(page,param.getUsername(),param.getHouseNum());
    }

    @Override
    public void saveFeeWater(FeeWater feeWater) {
        this.baseMapper.insert(feeWater);
    }

    @Override
    public void updateFeeWater(FeeWater feeWater) {
        this.baseMapper.updateById(feeWater);
    }
}
