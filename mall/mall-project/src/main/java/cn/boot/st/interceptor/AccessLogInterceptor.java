package cn.boot.st.interceptor;

import cn.boot.st.common.framwork.vo.CommonResult;
import cn.boot.st.dataobject.AccessLog;
import cn.boot.st.systemlog.SystemLogService;
import cn.boot.st.web.constant.CommonMallConstants;
import cn.boot.st.web.utils.CommonWebUtil;
import cn.boot.st.web.utils.HttpUtil;
import cn.boot.st.web.utils.TraceLogUtils;
import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name}")
    private String applicationName;
    @Reference

    @Autowired
    private SystemLogService systemLogService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录当前时间
        CommonWebUtil.setAccessStartTime(request, new Date());
        String traceId = TraceLogUtils.getTraceId();
        MDC.put(CommonMallConstants.LOG_TRACE_ID, traceId);
        request.setAttribute("traceId", traceId);
        MDC.put(CommonMallConstants.LOG_TRACE_ID, traceId);

        log.info("preHandle...");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AccessLog accessLog = new AccessLog();
        try {
            String traceId = (String) request.getAttribute("traceId");
            accessLog.setTraceId(traceId);
            // 初始化 accessLog
            initAccessLog(accessLog, request);
            // 执行插入 accessLog
            addAccessLog(accessLog);
            // TODO 提升：暂时不考虑 ELK 的方案。而是基于 MySQL 存储。如果访问日志比较多，需要定期归档。
        } catch (Throwable th) {
            log.error("[afterCompletion][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        } finally {
            MDC.remove(CommonMallConstants.LOG_TRACE_ID);
        }
    }

    private void initAccessLog(AccessLog accessLog, HttpServletRequest request) {

        // 设置账号编号
//        accessLog.setAccountId(CommonWebUtil.getAccountId(request));
        // 设置访问结果
        CommonResult result = CommonWebUtil.getCommonResult(request);
        Assert.isTrue(result != null, "result 必须非空");

        accessLog.setApplicationName(applicationName);

        accessLog.setErrorCode(result.getCode());
        accessLog.setErrorMessage(result.getMessage());
        // 设置其它字段
//        accessLog.setTraceId(traceId);
        accessLog.setApplicationName(applicationName);
        accessLog.setUri(request.getRequestURI()); // TODO 可以使用 Swagger 的 @ApiOperation 注解。
        accessLog.setQueryString(HttpUtil.buildQueryString(request));
        accessLog.setMethod(request.getMethod());
        accessLog.setUserAgent(HttpUtil.getUserAgent(request));
        accessLog.setIp(HttpUtil.getIp(request));
        accessLog.setStartTime(CommonWebUtil.getAccessStartTime(request));
        accessLog.setResponseTime((int) (System.currentTimeMillis() - accessLog.getStartTime().getTime())); // 默认响应时间设为 0
    }

    @Async // 异步入库
    public void addAccessLog(AccessLog accessLog) {
        try {
            systemLogService.addAccessLog(accessLog);
        } catch (Throwable th) {
            log.error("[addAccessLog][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        }
    }


}
