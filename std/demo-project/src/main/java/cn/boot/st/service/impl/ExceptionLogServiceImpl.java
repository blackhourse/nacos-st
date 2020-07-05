package cn.boot.st.service.impl;

import cn.boot.st.dao.ExceptionLogMapper;
import cn.boot.st.dataobject.ExceptionLog;
import cn.boot.st.service.ExceptionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-04 14:13
 **/
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {
}
