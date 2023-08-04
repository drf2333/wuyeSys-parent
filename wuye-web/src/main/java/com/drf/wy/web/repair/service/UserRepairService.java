package com.drf.wy.web.repair.service;


import com.drf.wy.web.repair.po.UserRepair;
import com.drf.wy.web.repair.po.UserRepairParam;

import java.util.Map;

/**
 * @Auther：杜瑞丰
 * @Date：2023/8/4 9:43
 * @Project_Name:wuyeSys-parent
 */
public interface UserRepairService {
    Map<String, Object> getList(UserRepairParam param);

    Map<String, Object> myList(UserRepairParam param);

    void addRepair(UserRepair userRepair);

    void updateRepair(UserRepair userRepair);

    void deleteRepair(String id);
}
