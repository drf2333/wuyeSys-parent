package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.FeePower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.FeePowerParam;

/**
 * <p>
 * 电费表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
public interface IFeePowerService extends IService<FeePower> {

    IPage<FeePower> getList(FeePowerParam param);

    void saveFeePower(FeePower feePower);

    void updateFeePower(FeePower feePower);

}
