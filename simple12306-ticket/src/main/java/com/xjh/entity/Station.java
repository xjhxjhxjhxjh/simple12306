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
 * @date 2020/2/18 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_station")
public class Station implements Serializable {
    @TableId(value = "sId", type = IdType.AUTO)
    private Integer sId;
    @TableField("sStationName")
    private String sStationName;
    @TableField("sCityName")
    private String sCityName;
}
