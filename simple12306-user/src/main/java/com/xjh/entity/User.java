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
 * @date 2020/2/23 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_user")
public class User implements Serializable {
    @TableId(value = "uId", type = IdType.ID_WORKER)
    private Long uId;
    @TableField("uName")
    private String uName;
    @TableField("uMobile")
    private String uMobile;
    @TableField("uEmail")
    private String uEmail;
    @TableField("uSalt")
    private String uSalt;
    @TableField("uIdCard")
    private String uIdCard;
    @TableField("uPassword")
    private String uPassword;
}
