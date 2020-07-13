package cn.boot.st.web.utils;


import cn.boot.st.common.framwork.base.ResponseData;
import cn.boot.st.common.framwork.constant.CommonMallConstants;

import javax.servlet.ServletRequest;
import java.util.Date;

public class CommonWebUtil {

    public static Integer getAccountId(ServletRequest request) {
        return (Integer) request.getAttribute(CommonMallConstants.REQUEST_ATTR_USER_ID_KEY);
    }

    public static void setAccountId(ServletRequest request, Integer userId) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_USER_ID_KEY, userId);
    }

    public static ResponseData getCommonResult(ServletRequest request) {
        return (ResponseData) request.getAttribute(CommonMallConstants.REQUEST_ATTR_COMMON_RESULT);
    }

    public static void setCommonResult(ServletRequest request, ResponseData result) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_COMMON_RESULT, result);
    }

    public static void setAccessStartTime(ServletRequest request, Date startTime) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_ACCESS_START_TIME, startTime);
    }

    public static Date getAccessStartTime(ServletRequest request) {
        return (Date) request.getAttribute(CommonMallConstants.REQUEST_ATTR_ACCESS_START_TIME);
    }

}
