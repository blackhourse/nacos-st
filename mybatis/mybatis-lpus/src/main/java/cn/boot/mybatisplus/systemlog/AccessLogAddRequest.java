package cn.boot.mybatisplus.systemlog;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 访问日志添加 Request
 */
@Data
@Accessors(chain = true)
public class AccessLogAddRequest implements Serializable {

    /**
     * 用户编号 - 空
     */
    public static final Integer ACCOUNT_ID_NULL = 0;

    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 账号编号
     */
    private Integer accountId;
    @NotNull(message = "应用名不能为空")
    private String applicationName;
    @NotNull(message = "访问地址不能为空")
    private String uri;
    @NotNull(message = "请求参数不能为空")
    private String queryString;
    @NotNull(message = "http 请求方法不能为空")
    private String method;
    @NotNull(message = "User-Agent 不能为空")
    private String userAgent;
    @NotNull(message = "ip 不能为空")
    private String ip;
    @NotNull(message = "请求时间不能为空")
    private Date startTime;
    @NotNull(message = "响应时长不能为空")
    private Integer responseTime;
    @NotNull(message = "错误码不能为空")
    private Integer errorCode;
    /**
     * 错误提示
     */
    private String errorMessage;

}
