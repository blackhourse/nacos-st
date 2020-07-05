package cn.boot.mybatisplus.exception;

import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.base.SysErrorCodeEnum;
import cn.boot.mybatisplus.dataobject.ExceptionLog;
import cn.boot.mybatisplus.systemlog.SystemLogService;
import cn.boot.mybatisplus.utils.ExceptionUtil;
import cn.boot.mybatisplus.utils.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-03 16:15
 **/
@Slf4j
@Component
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private SystemLogService systemLogService;

    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * 逻辑异常
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, BizException ex) {
        log.debug("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    // Spring MVC 参数不正确
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage() + ":" + ex.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        log.info("[constraintViolationExceptionHandler]", ex);
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(),
                SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + detailMessage.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("[exceptionHandler]", e);
        // 插入异常日志
        ExceptionLog exceptionLog = new ExceptionLog();
        try {
            // 增加异常计数 metrics TODO 暂时去掉
//            EXCEPTION_COUNTER.increment();
            // 初始化 exceptionLog
            initExceptionLog(exceptionLog, req, e);
            // 执行插入 exceptionLog
            addExceptionLog(exceptionLog);
        } catch (Throwable th) {
            log.error("[exceptionHandler][插入访问日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
        }
        // 返回 ERROR CommonResult
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

    private void initExceptionLog(ExceptionLog exceptionLog, HttpServletRequest request, Exception e) {
        // 设置账号编号
//        exceptionLog.setAccountId(CommonWebUtil.getAccountId(request));
//        exceptionLog.setAccountId(123);
        // 设置异常字段
        exceptionLog.setExceptionName(e.getClass().getName());
        exceptionLog.setExceptionMessage(ExceptionUtil.getMessage(e));
        exceptionLog.setExceptionRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
        exceptionLog.setExceptionStackTrace(ExceptionUtil.getStackTrace(e));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
        StackTraceElement stackTraceElement = stackTraceElements[0];
        exceptionLog.setExceptionClassName(stackTraceElement.getClassName());
        exceptionLog.setExceptionFileName(stackTraceElement.getFileName());
        exceptionLog.setExceptionMethodName(stackTraceElement.getMethodName());
        exceptionLog.setExceptionLineNumber(stackTraceElement.getLineNumber());
        // 设置其它字段
        exceptionLog.setTraceId("123")
                .setApplicationName(applicationName)
                .setUri(request.getRequestURI()) // TODO 提升：优化，可以使用 Swagger 的 @ApiOperation 注解。
                .setQueryString(HttpUtil.buildQueryString(request))
                .setMethod(request.getMethod())
                .setUserAgent(HttpUtil.getUserAgent(request))
                .setIp(HttpUtil.getIp(request))
                .setExceptionTime(new Date());
    }


    @Async
    public void addExceptionLog(ExceptionLog exceptionLog) {
        try {
            systemLogService.addExceptionLog(exceptionLog);
        } catch (Throwable th) {
            log.error("[addAccessLog][插入异常日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
        }

    }

}
