package cn.boot.mybatisplus.service.impl;

import cn.boot.mybatisplus.dao.ExceptionLogMapper;
import cn.boot.mybatisplus.dataobject.ExceptionLog;
import cn.boot.mybatisplus.service.ExceptionLogService;
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
