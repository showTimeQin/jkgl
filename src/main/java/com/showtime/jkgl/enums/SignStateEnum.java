package com.showtime.jkgl.enums;

public enum SignStateEnum {
    NOT_SIGN((byte)0, "未签到"),
    FINISH_SIGN((byte)1,"签到完成"),
    AFTER_SIGN((byte)2, "补签")
    ;
    private Byte code;
    private String msg;

    SignStateEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    SignStateEnum() {
    }

    public Byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
