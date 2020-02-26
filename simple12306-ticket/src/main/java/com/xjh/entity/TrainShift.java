package com.xjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_trainShift")
public class TrainShift implements Serializable {
    @TableId(value = "tId", type = IdType.AUTO)
    private Integer tId;
    @TableField("tSeatNum")
    private Integer tSeatNum;
    @TableField("tTime")
    private LocalDateTime tTime;
    @TableField("tRouteId")
    private Integer tRouteId;
}