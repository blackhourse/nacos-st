package cn.boot.st.systemlog;


import cn.boot.st.common.framwork.base.ResponseData;
import cn.boot.st.dataobject.AccessLog;
import cn.boot.st.dataobject.ExceptionLog;

public interface SystemLogService {

    ResponseData<Boolean> addAccessLog(AccessLog accessLog);

    ResponseData<Boolean> addExceptionLog(ExceptionLog exceptionLog);

}
