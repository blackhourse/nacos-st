package cn.boot.st.systemlog;

import cn.boot.st.common.framwork.base.Response;
import cn.boot.st.common.framwork.base.ResponseData;
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
    public ResponseData<Boolean> addAccessLog(AccessLog accessLog) {
        accessLogMapper.insert(accessLog);
        return Response.ok(true);
    }

    @Override
    public ResponseData<Boolean> addExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.insert(exceptionLog);
        return Response.ok(true);
    }
}
