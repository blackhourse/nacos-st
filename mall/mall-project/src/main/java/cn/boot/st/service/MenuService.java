package cn.boot.st.service;

import cn.boot.st.dataobject.Menu;
import cn.boot.st.vo.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 14:12
 **/
public interface MenuService extends IService<Menu> {
    void addMenu(Menu menu);

    /**
     * 获取菜单列表
     *
     * @param userId
     * @return
     */
    List<MenuVo> queryMenus(Long userId);

    /**
     * 获取菜单树
     *
     * @return
     */
    List<MenuVo> queryMenuTree();

    /**
     * 修改菜单
     * @param menu
     */
    void updateMenu(Menu menu);

    void delMenu(Menu menu);

}
