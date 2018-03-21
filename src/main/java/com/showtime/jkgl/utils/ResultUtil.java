package com.showtime.jkgl.utils;


import com.showtime.jkgl.model.base.Result;

public class ResultUtil {

    public static Result success(Object object){
        return new Result(0, "成功",object);
    }

    public static Result error(Integer code, String msg){
        return new Result(code, msg, null);
    }
}
