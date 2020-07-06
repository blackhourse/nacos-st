package cn.boot.st.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName(value = "access_log")
public class AccessLog {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String traceId;

    private Integer userId;

    private Byte userType;

    private String applicationName;

    private String uri;

    private String queryString;

    private String method;

    private String userAgent;

    private String ip;

    private Date startTime;

    private Integer responseTime;

    private Integer errorCode;

    private String errorMessage;

    private Date createTime;

    private String responseInfo;
}