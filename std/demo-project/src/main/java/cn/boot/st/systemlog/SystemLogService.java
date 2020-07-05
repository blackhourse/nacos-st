package cn.boot.st.systemlog;


import cn.boot.st.base.CommonResult;
import cn.boot.st.dataobject.AccessLog;
import cn.boot.st.dataobject.ExceptionLog;

public interface SystemLogService {

    CommonResult<Boolean> addAccessLog(AccessLog accessLog);

    CommonResult<Boolean> addExceptionLog(ExceptionLog exceptionLog);

}
