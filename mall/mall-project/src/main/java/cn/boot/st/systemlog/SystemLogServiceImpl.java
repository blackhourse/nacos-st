package cn.boot.st.systemlog;

import cn.boot.st.common.framwork.vo.CommonResult;
import cn.boot.st.dao.AccessLogMapper;
import cn.boot.st.dao.ExceptionLogMapper;
import cn.boot.st.dataobject.AccessLog;
import cn.boot.st.dataobject.ExceptionLog;
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
