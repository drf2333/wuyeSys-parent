package com.drf.wy.utils;


import com.drf.wy.web.system.entity.SysMenu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/24 15:07
 * @Project_Name:wuyeSys-parent
 */
public class MakeMenuTree {
    public static List<SysMenu> makeTree(List<SysMenu> menuList, Integer pid) {
        List<SysMenu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(dom -> {
                    SysMenu menu = new SysMenu();
                    BeanUtils.copyProperties(dom, menu);
                    List<SysMenu> menus = makeTree(menuList, dom.getMenuId());
                    menu.setChildren(menus);
                    list.add(menu);
                });
        return list;
    }

}
