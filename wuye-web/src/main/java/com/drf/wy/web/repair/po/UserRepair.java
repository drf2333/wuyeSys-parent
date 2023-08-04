package com.drf.wy.web.repair.po;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @Auther：杜瑞丰
 * @Date：2023/8/4 9:39
 * @Project_Name:wuyeSys-parent
 */
@Data
@Document(collection = "user_repair")
public class UserRepair {

    @Id
    private String id;
    //报修人
    private Integer userId;
    //联系电话
    private String phone;
    //联系地址
    private String address;
    //维修内容
    private String content;
    //维修时间
    private LocalDateTime commitTime;
    //维修状态 【0 未维修】【1已维修】
    private Integer status;
    //报修视频id
    private String videoId;
}
