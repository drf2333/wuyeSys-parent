package com.drf.wy.web.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.web.system.entity.AssignHouseParam;
import com.drf.wy.web.system.entity.LiveUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drf.wy.web.system.entity.LiveUserParam;

/**
 * <p>
 * 业主表 服务类
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-21
 */
public interface ILiveUserService extends IService<LiveUser> {

    IPage<LiveUser> getList(LiveUserParam param);

    void saveLiveUser(LiveUser liveUser);

    void editLiveUser(LiveUser liveUser);

    LiveUser getUser(Integer userId);

    void assignHouse(AssignHouseParam param);

    void returnHouse(AssignHouseParam param);
}
