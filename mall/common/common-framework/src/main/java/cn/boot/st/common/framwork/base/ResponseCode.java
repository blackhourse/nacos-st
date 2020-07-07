package cn.boot.st.common.framwork.base;

/**
 * REST API 响应码
 *
 */
public enum ResponseCode {
    SUCCESS_CODE(200, "成功"),
    PARAM_ERROR_CODE(400, "参数错误"),
    FORBIDDEN_CODE(403, "禁止访问"),
    NOT_FOUND_CODE(404, "资源不存在"),
    REQUEST_METHOD_NOT_SUPPORTED_CODE(405, "不支持的请求方法"),
    SERVER_LIMIT_CODE(429, "服务限流"),
    SERVER_ERROR_CODE(500, "服务器错误"),
    SERVER_DOWNGRADE_CODE(700, "服务降级"),
    TOKEN_TIMEOUT_CODE(800, "登录信息过期"),


    /**
     * 用户服务
     * 前三位 100
     * 中两位 01 通用异常 02 登录业务
     * 后三位 具体错误
     */
    USER_EXCEPTION_CODE(10001001, "用户通用异常"),
    USER_LOGIN_ERROR_CODE(10002001, "用户名或密码错误"),

    SYS_ERROR(2001001000, "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(2001001001, "参数缺失"),
    VALIDATION_REQUEST_PARAM_ERROR(2001001002, "参数校验不正确"),
    TOKEN_EXPIRED(2001001003, "登录已失效，请重新登录"),
    TOKEN_INVALID(2001001004, "token验证失败"),
    INTER_NO_ACCESS(2001001005, "接口无访问权限");


    private int code;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
