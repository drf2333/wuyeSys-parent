package com.drf.wy.web.repair.po;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/8/4 9:40
 * @Project_Name:wuyeSys-parent
 */
@Data
public class UserRepairParam extends BaseParam {
    //报修人
    private Integer userId;
    //维修内容
    private String content;
}
