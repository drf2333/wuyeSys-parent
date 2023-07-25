package com.drf.wy.web.system.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/24 20:19
 * @Project_Name:wuyeSys-parent
 */
@Data
public class CheckMenuTreeVo {
    //权限菜单树
    private List<SysMenu> tree = new ArrayList<>();
    //原来分配的菜单id
    private List<Integer> checkMenuIds;
}
