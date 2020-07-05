package cn.boot.mybatisplus.dao;

import cn.boot.mybatisplus.dataobject.AccessLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessLogMapper extends BaseMapper<AccessLog> {

}