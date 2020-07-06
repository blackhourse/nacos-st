package cn.boot.st.security.constant;

public interface CommonMallConstants {

    // 全局请求路径枚举类，用于定义不同用户类型的根请求路径
    /**
     * 根路径 - 用户
     */
    String ROOT_PATH_USER = "/users";
    /**
     * 根路径 - 管理员
     */
    String ROOT_PATH_ADMIN = "/admins";

    // HTTP Request Attr
    /**
     * HTTP Request Attr - 账号编号
     */
    String REQUEST_ATTR_USER_ID_KEY = "account_id";
    /**
     * HTTP Request Attr - Controller 执行返回
     */
    String REQUEST_ATTR_COMMON_RESULT = "common_result";
    /**
     * HTTP Request Attr - 访问开始时间
     */
    String REQUEST_ATTR_ACCESS_START_TIME = "access_start_time";

    /**
     * 日志追踪id
     */
    String LOG_TRACE_VAL = "traceId-";

    String LOG_TRACE_ID = "traceId";
}
