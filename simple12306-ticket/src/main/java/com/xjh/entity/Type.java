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

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_type")
public class Type implements Serializable {
    @TableId(value = "tId", type = IdType.AUTO)
    private Integer tId;
    @TableField("tType")
    private String tType;
    @TableField("tPrice")
    private BigDecimal tPrice;
    @TableField("tCapacity")
    private Integer tCapacity;
}
