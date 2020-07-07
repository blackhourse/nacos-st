package cn.boot.st.common.framwork.utils;


import cn.boot.st.common.framwork.constant.CommonMallConstants;

/**
 * @Author: tf
 * @Date: 2019/6/12 11:05
 */
public class TraceLogUtils {

    public static String getTraceId() {
        StringBuilder traceId = new StringBuilder(CommonMallConstants.LOG_TRACE_VAL);
        traceId.append(System.currentTimeMillis());
        traceId.append(RandomUtils.getRandomNumber(4));
        return traceId.toString();
    }
}
