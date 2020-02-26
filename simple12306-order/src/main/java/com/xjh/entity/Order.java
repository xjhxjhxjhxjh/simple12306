package com.xjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 13:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_order")
public class Order implements Serializable {
    @TableId(value = "oId", type = IdType.ID_WORKER)
    private Long oId;
    @TableField("oUserId")
    private Long oUserId;
    @TableField("oStartRouteId")
    private Integer oStartRouteId;
    @TableField("oEndRouteId")
    private Integer oEndRouteId;
    @TableField("oPayTime")
    private LocalDateTime oPayTime;
    @TableField("oDate")
    private LocalDate oDate;
}