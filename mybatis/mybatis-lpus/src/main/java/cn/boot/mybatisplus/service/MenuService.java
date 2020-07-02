package cn.boot.mybatisplus.service;

import cn.boot.mybatisplus.dataobject.Menu;
import cn.boot.mybatisplus.vo.MenuVo;
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
}
