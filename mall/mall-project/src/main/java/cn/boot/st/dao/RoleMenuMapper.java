package cn.boot.st.dao;

import cn.boot.st.dataobject.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}