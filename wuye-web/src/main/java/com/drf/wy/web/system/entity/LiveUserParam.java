package com.drf.wy.web.system.entity;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/25 17:23
 * @Project_Name:wuyeSys-parent
 */
@Data
public class LiveUserParam extends BaseParam {
    //姓名
    private String trueName;
    //手机号
    private String phone;
}
