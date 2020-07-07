package cn.boot.st.exception;

import cn.boot.st.common.framwork.base.Response;
import cn.boot.st.common.framwork.base.ResponseCode;
import cn.boot.st.common.framwork.base.ResponseData;
import cn.boot.st.common.framwork.exception.ServiceException;
import cn.boot.st.common.framwork.utils.ExceptionUtil;
import cn.boot.st.common.framwork.utils.HttpUtil;
import cn.boot.st.dataobject.ExceptionLog;
import cn.boot.st.systemlog.SystemLogService;
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
    @ExceptionHandler(value = ServiceException.class)
    public ResponseData serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        log.debug("[serviceExceptionHandler]", ex);
        this.saveExceptionLog(req, ex);
        return Response.fail(ex.getMessage(), ex.getCode());
    }

    // Spring MVC 参数不正确
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseData missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        this.saveExceptionLog(req, ex);
        return Response.fail(ResponseCode.MISSING_REQUEST_PARAM_ERROR.getMessage() + ":" + ex.getMessage()
                , ResponseCode.MISSING_REQUEST_PARAM_ERROR.getCode());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseData constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        log.info("[constraintViolationExceptionHandler]", ex);
        this.saveExceptionLog(req, ex);
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return Response.fail(ResponseCode.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + detailMessage.toString()
                , ResponseCode.VALIDATION_REQUEST_PARAM_ERROR.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("[exceptionHandler]", e);
        this.saveExceptionLog(req, e);
        /*log.error("[exceptionHandler]", e);
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
        }*/
        // 返回 ERROR CommonResult
        return Response.fail(ResponseCode.SYS_ERROR.getMessage(), ResponseCode.SYS_ERROR.getCode());
    }


    private void saveExceptionLog(HttpServletRequest req, Exception e) {
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
