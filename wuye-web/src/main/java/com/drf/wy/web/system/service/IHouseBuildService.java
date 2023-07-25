package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.HouseBuild;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.HouseBuildParam;

/**
 * <p>
 * 楼栋表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface IHouseBuildService extends IService<HouseBuild> {
    IPage<HouseBuild> getList(HouseBuildParam param);

}
