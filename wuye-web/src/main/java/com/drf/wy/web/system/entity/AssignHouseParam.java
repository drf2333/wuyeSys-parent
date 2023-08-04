package com.drf.wy.web.system.entity;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/25 19:41
 * @Project_Name:wuyeSys-parent
 */
@Data
public class AssignHouseParam extends BaseParam {

    private Integer userId;
    private Integer houseId;
}
