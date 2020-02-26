package com.xjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_route")
public class Route implements Serializable {
    @TableId(value = "rId", type = IdType.AUTO)
    private Integer rId;
    @TableField("rName")
    private String rName;
    @TableField("rArriveTime")
    private String rArriveTime;
    @TableField("rLeaveTime")
    private String rLeaveTime;
    @TableField("rMileage")
    private Integer rMileage;
    @TableField("rStationId")
    private Integer rStationId;
    @TableField("rTypeId")
    private Integer rTypeId;
}
