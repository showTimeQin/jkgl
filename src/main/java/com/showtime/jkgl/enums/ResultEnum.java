package com.showtime.jkgl.enums;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),

    UNKNOW_ROLE(100, "未知角色"),
    EMPTY_PASSWORD(101, "密码不能为空"),
    ERROR_EXCEL_EXT(102, "EXCEL格式错误")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
