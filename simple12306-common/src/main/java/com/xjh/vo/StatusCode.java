package com.xjh.vo;

/**
 * @author xjhxjhxjh
 * @date 2020/2/16 10:11
 */
public enum StatusCode {
    //
    SUCCESS("2000", "成功"),
    LOGIN_ERROR("登录失败", "2001"),
    ACCESS_ERROR("2002", "权限不足"),
    REMOTE_ERROR("2003", "远程调用失败"),
    PEP_ERROR("2004", "重复操作"),
    SERVER_ERROR("2005", "系统繁忙") ,
    Register_ERROR("2006", "注册失败") ;

    private String code;
    private String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
