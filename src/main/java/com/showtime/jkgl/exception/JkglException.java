package com.showtime.jkgl.exception;


import com.showtime.jkgl.enums.ResultEnum;

public class JkglException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public JkglException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
