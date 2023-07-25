package com.drf.wy.web.system.entity;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/25 9:43
 * @Project_Name:wuyeSys-parent
 */
@Data
public class HouseBuildParam extends BaseParam {
    private String buildName;
    private Integer type;
}
