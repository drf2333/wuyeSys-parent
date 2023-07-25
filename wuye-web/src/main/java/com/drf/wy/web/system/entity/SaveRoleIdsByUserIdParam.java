package com.drf.wy.web.system.entity;


import lombok.Data;

import java.util.List;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/24 19:18
 * @Project_Name:wuyeSys-parent
 */
@Data
public class SaveRoleIdsByUserIdParam {
    private Integer userId;
    private List<Integer> roleIds;
}
