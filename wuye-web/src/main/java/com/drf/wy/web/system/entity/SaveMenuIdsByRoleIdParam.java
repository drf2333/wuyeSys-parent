package com.drf.wy.web.system.entity;


import lombok.Data;

import java.util.List;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/24 20:19
 * @Project_Name:wuyeSys-parent
 */
@Data
public class SaveMenuIdsByRoleIdParam {
    private Integer roleId;
    private List<Integer> menuIds;
}
