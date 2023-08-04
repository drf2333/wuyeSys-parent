package com.drf.wy.web.complaint.po;


import com.drf.wy.utils.BaseParam;
import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/27 14:51
 * @Project_Name:wuyeSys-parent
 */
@Data
public class UserComplaintParam extends BaseParam {

    //投诉人
    private Integer userId;
    //标题
    private String title;
    //内容
    private String content;
}
