package com.showtime.jkgl.handle;




import com.showtime.jkgl.exception.JkglException;
import com.showtime.jkgl.model.base.Result;
import com.showtime.jkgl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof JkglException) {
            JkglException signException = (JkglException) e;
            return ResultUtil.error(signException.getCode(), signException.getMessage());
        }
        logger.error("[系统错误] {}", e);
        return ResultUtil.error(-1, "未知错误");
    }
}
