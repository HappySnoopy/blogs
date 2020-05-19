package net.loyintean.springmvcbase.common.aop;

import net.loyintean.springmvcbase.common.bean.BaseResult;
import net.loyintean.springmvcbase.common.exception.BizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handle() {
        return new ExceptionResult();
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public BaseResult handle(BizException e) {
        return new ExceptionResult(e);
    }

    private static class ExceptionResult extends BaseResult {

        private ExceptionResult() {
            super("9999", "系统发生未知异常。");
        }

        private ExceptionResult(BizException e) {
            super(e.getCode(), e.getMessage());
        }
    }
}
