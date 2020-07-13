package cn.boot.st.dao;

import cn.boot.st.dataobject.Menu;
import cn.boot.st.web.query.QueryWrapperX;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取userId menucode 关联总数
     *
     * @param userId
     * @param menuCode
     * @return
     */
    int selectAuthByUserIdAndMenuCode(@Param("userId") Long userId, @Param("menuCode") String menuCode);

    default Menu queryMenu(Integer id) {
        QueryWrapperX queryWrapperX = new QueryWrapperX();
        queryWrapperX.eq("id", id);
        return selectOne(new QueryWrapperX<Menu>().in("id", id));
    }


}