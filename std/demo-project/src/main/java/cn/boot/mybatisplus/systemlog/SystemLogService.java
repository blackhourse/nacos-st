package cn.boot.mybatisplus.systemlog;


import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.dataobject.AccessLog;
import cn.boot.mybatisplus.dataobject.ExceptionLog;

public interface SystemLogService {

    CommonResult<Boolean> addAccessLog(AccessLog accessLog);

    CommonResult<Boolean> addExceptionLog(ExceptionLog exceptionLog);

}
