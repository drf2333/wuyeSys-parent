package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drf.wy.web.system.entity.HouseList;
import com.drf.wy.web.system.entity.HouseListParam;
import com.drf.wy.web.system.mapper.HouseListMapper;
import com.drf.wy.web.system.service.IHouseListService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房屋表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@Service
public class HouseListServiceImpl extends ServiceImpl<HouseListMapper, HouseList> implements IHouseListService {

    @Override
    public IPage<HouseList> getList(HouseListParam param) {
        //构造分页对象
        IPage<HouseList> page = new Page<>(param.getCurrentPage(), param.getPageSize());

        return this.baseMapper.getList(page, param.getBuildName(), param.getUnitName(), param.getHouseNum(), param.getStatus());
    }
}

