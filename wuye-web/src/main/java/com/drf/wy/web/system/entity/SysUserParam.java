package com.drf.wy.web.system.entity;

import com.drf.wy.utils.BaseParam;
import lombok.Data;


@Data
public class SysUserParam extends BaseParam {
    private String trueName;
    private String phone;
}
