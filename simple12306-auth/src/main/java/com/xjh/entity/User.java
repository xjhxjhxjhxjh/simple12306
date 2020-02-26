package com.xjh.entity;

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
public class User implements Serializable {
    private Long uId;
    private String uName;
    private String uMobile;
    private String uEmail;
    private String uSalt;
    private String uIdCard;
    private String uPassword;
}
