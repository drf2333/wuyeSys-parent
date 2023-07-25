package com.drf.wy.web.system.entity;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/25 11:28
 * @Project_Name:wuyeSys-parent
 */
@Data
public class HouseListParam extends BaseParam {

    //楼栋名称
    private String buildName;
    //使用状态
    private Integer status;
    //单元名称
    private String unitName;
    //房屋编号
    private String houseNum;
}
