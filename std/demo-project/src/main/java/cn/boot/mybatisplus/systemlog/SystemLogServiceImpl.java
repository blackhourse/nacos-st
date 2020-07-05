package cn.boot.mybatisplus.systemlog;

import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.dao.AccessLogMapper;
import cn.boot.mybatisplus.dao.ExceptionLogMapper;
import cn.boot.mybatisplus.dataobject.AccessLog;
import cn.boot.mybatisplus.dataobject.ExceptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private AccessLogMapper accessLogMapper;
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public CommonResult<Boolean> addAccessLog(AccessLog accessLog) {
        accessLogMapper.insert(accessLog);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> addExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.insert(exceptionLog);
        return CommonResult.success(true);
    }
}
