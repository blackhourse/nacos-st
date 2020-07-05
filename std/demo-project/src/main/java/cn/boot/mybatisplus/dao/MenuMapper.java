package cn.boot.mybatisplus.dao;

import cn.boot.mybatisplus.dataobject.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

   /**
    * 获取userId menucode 关联总数
    * @param userId
    * @param menuCode
    * @return
    */
   int selectAuthByUserIdAndMenuCode(@Param("userId") Long userId, @Param("menuCode") String menuCode);

}