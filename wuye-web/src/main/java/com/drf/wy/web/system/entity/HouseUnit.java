package com.drf.wy.web.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 单元表
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@Getter
@Setter
@TableName("house_unit")
@ApiModel(value = "HouseUnit对象", description = "单元表")
public class HouseUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单元主键")
    @TableId(value = "unit_id", type = IdType.AUTO)
    private Integer unitId;

    @ApiModelProperty("楼栋id(外键)")
    private Integer buildId;

    @ApiModelProperty("单元名称")
    private String unitName;

    @ApiModelProperty("排序字段")
    private Integer orderNum;

    @ApiModelProperty("假删【0删除】【1未删除】")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("楼栋名称")
    @TableField(exist = false)
    private String buildName;


}
