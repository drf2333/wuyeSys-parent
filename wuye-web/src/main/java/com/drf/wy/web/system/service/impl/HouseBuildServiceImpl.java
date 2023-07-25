package com.drf.wy.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drf.wy.web.system.entity.HouseBuild;
import com.drf.wy.web.system.entity.HouseBuildParam;
import com.drf.wy.web.system.mapper.HouseBuildMapper;
import com.drf.wy.web.system.service.IHouseBuildService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 * 楼栋表 服务实现类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@Service
public class HouseBuildServiceImpl extends ServiceImpl<HouseBuildMapper, HouseBuild> implements IHouseBuildService {

    @Override
    public IPage<HouseBuild> getList(HouseBuildParam param) {
        String buildName = param.getBuildName();
        Integer type = param.getType();
        //构建一个分页对象
        IPage<HouseBuild> page = new Page<>(param.getCurrentPage(), param.getPageSize());

        //构建查询条件
        LambdaQueryWrapper<HouseBuild> query = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(buildName)){
            query.like(HouseBuild::getBuildName,buildName);
        }
        if (!Objects.isNull(type)){
            query.eq(HouseBuild::getType,type);
        }
        return this.baseMapper.selectPage(page,query);
    }
}
