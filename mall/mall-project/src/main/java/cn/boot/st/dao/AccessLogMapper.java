package cn.boot.st.dao;

import cn.boot.st.dataobject.AccessLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessLogMapper extends BaseMapper<AccessLog> {

}