package cn.boot.st.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName(value = "exception_log")
public class ExceptionLog {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String traceId;

    private Integer userId;

    private Integer userType;

    private String applicationName;

    private String uri;

    private String queryString;

    private String method;

    private String userAgent;

    private String ip;

    private Date exceptionTime;

    private String exceptionName;

    private String exceptionClassName;

    private String exceptionFileName;

    private String exceptionMethodName;

    private Integer exceptionLineNumber;

    private Date createTime;

    private String exceptionMessage;

    private String exceptionRootCauseMessage;

    private String exceptionStackTrace;
}