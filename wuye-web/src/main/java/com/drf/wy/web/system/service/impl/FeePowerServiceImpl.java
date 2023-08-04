package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drf.wy.web.system.entity.FeePower;
import com.drf.wy.web.system.entity.FeePowerParam;
import com.drf.wy.web.system.mapper.FeePowerMapper;
import com.drf.wy.web.system.service.IFeePowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电费表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@Service
public class FeePowerServiceImpl extends ServiceImpl<FeePowerMapper, FeePower> implements IFeePowerService {



    @Override
    public IPage<FeePower> getList(FeePowerParam param) {
        IPage<FeePower> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        return this.baseMapper.getList(page,param.getUsername(),param.getHouseNum());

    }

    @Override
    public void saveFeePower(FeePower feePower) {
        this.baseMapper.insert(feePower);
    }

    @Override
    public void updateFeePower(FeePower feePower) {
        this.baseMapper.updateById(feePower);
    }
}
