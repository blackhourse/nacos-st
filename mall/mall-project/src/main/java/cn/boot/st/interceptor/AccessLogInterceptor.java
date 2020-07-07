package cn.boot.st.interceptor;

import cn.boot.st.common.framwork.base.ResponseData;
import cn.boot.st.common.framwork.constant.CommonMallConstants;
import cn.boot.st.common.framwork.utils.HttpUtil;
import cn.boot.st.common.framwork.utils.TraceLogUtils;
import cn.boot.st.dataobject.AccessLog;
import cn.boot.st.jwt.JwtIgnore;
import cn.boot.st.jwt.JwtTokenUtil;
import cn.boot.st.jwt.WebContextUtil;
import cn.boot.st.security.utils.CommonWebUtil;
import cn.boot.st.systemlog.SystemLogService;
import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.application.name}")
    private String applicationName;
    @Reference

    @Autowired
    private SystemLogService systemLogService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //        ----------日志id--------
        // 记录当前时间
        CommonWebUtil.setAccessStartTime(request, new Date());
        String traceId = TraceLogUtils.getTraceId();
        MDC.put(CommonMallConstants.LOG_TRACE_ID, traceId);
        request.setAttribute("traceId", traceId);
        MDC.put(CommonMallConstants.LOG_TRACE_ID, traceId);
        log.info("preHandle...");
        // 从http请求头中取出token
        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        //如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //如果是方法探测，直接通过
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //如果方法有JwtIgnore注解，直接通过
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if (jwtIgnore.value()) {
                return true;
            }
        }
        Assert.notNull(token, "token为空，鉴权失败！");
        //验证，并获取token内部信息
        String userToken = JwtTokenUtil.verifyToken(token);

        //将token放入本地缓存
        WebContextUtil.setUserToken(userToken);
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
            WebContextUtil.removeUserToken();
        }
    }

    private void initAccessLog(AccessLog accessLog, HttpServletRequest request) {

        // 设置账号编号
//        accessLog.setAccountId(CommonWebUtil.getAccountId(request));
        // 设置访问结果
        ResponseData result = CommonWebUtil.getCommonResult(request);
        Assert.isTrue(result != null, "result 必须非空");
        accessLog.setApplicationName(applicationName);
        accessLog.setErrorCode(result.getCode());
        accessLog.setErrorMessage(result.getMessage());
        accessLog.setResponseInfo(JSON.toJSONString(result));
        // 设置其它字段
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
