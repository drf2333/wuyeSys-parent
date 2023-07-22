package com.drf.wy.utils;


import lombok.Data;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 15:13
 * @Project_Name:wuyeSys-parent
 */
@Data
public class BaseParam {
    private Integer currentPage = 1;//当前页
    private Integer pageSize = 5;//每页参数展示大小
}
