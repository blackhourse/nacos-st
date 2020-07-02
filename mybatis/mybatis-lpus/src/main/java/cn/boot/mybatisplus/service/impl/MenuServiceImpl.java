package cn.boot.mybatisplus.service.impl;

import cn.boot.mybatisplus.dao.MenuMapper;
import cn.boot.mybatisplus.dataobject.Menu;
import cn.boot.mybatisplus.dataobject.RoleMenu;
import cn.boot.mybatisplus.dataobject.UserRole;
import cn.boot.mybatisplus.service.MenuService;
import cn.boot.mybatisplus.service.RoleMenuService;
import cn.boot.mybatisplus.service.UserRoleService;
import cn.boot.mybatisplus.vo.MenuVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 14:12
 **/
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper baseMapper;

    @Resource
    UserRoleService userRoleService;
    @Resource
    RoleMenuService roleMenuService;

    @Override
    public void addMenu(Menu menu) {
        //如果插入的当前节点为根节点，parentId指定为0
        if (menu.getParentId().longValue() == 0) {
            menu.setLevel(1);//根节点层级为1
            menu.setPath(null);//根节点路径为空
        } else {
            Menu parentMenu = baseMapper.selectById(menu.getParentId());
            if (parentMenu == null) {
                log.info("未查询到对应的父节点");
//                throw new CommonException("未查询到对应的父节点");
            }
            menu.setLevel(parentMenu.getLevel().intValue() + 1);
            if (StringUtils.isNotEmpty(parentMenu.getPath())) {
                menu.setPath(parentMenu.getPath() + "," + parentMenu.getId());
            } else {
                menu.setPath(parentMenu.getId().toString());
            }
        }
        //可以使用雪花算法，生成ID
        menu.setId(System.currentTimeMillis());
        super.save(menu);
    }

    @Override
    public List<MenuVo> queryMenus(Long userId) {
        // 先查询当前用户对应角色
        QueryWrapper<UserRole> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.lambda().eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleService.list(objectQueryWrapper);
        if (!CollectionUtils.isEmpty(userRoles)) {
            // 通过角色查询菜单(默认第一个角色)
            QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
            roleMenuQueryWrapper.lambda().eq(RoleMenu::getRoleId, userRoles.get(0).getRoleId());
            List<RoleMenu> roleMenus = roleMenuService.list(roleMenuQueryWrapper);
            if (!CollectionUtils.isEmpty(roleMenus)) {
                HashSet<Long> menuIds = new HashSet<>();
                for (RoleMenu roleMenu : roleMenus) {
                    menuIds.add(roleMenu.getMenuId());
                }
                // 查询出对应菜单
                QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
                menuQueryWrapper.lambda().in(Menu::getId, new ArrayList<>(menuIds));
                List<Menu> menus = super.list(menuQueryWrapper);
                if (!CollectionUtils.isEmpty(menus)) {
                    //将菜单下对应的父节点也一并全部查询出来
                    HashSet<Long> allMenuIds = new HashSet<>();
                    for (Menu menu : menus) {
                        allMenuIds.add(menu.getId());
                        //path，是用来来存放节点对应的父节点路径
                        if (StringUtils.isNotEmpty(menu.getPath())) {
                            String[] pathIds = StringUtils.split(menu.getPath(), ",");
                            for (String pathId : pathIds) {
                                allMenuIds.add(Long.valueOf(pathId));
                            }
                        }
                    }
                    // 查询出所有的菜单进行封装
                    QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().in(Menu::getId, new ArrayList<>(allMenuIds));
                    List<Menu> allMenus = super.list(queryWrapper);
                    return transferMenuVo(allMenus, 0L);
                }
            }
        }
        return null;
    }


    @Override
    public List<MenuVo> queryMenuTree() {
        Wrapper wrapper = new QueryWrapper<>().orderByAsc("level", "sort");
        List<Menu> allMenu = super.list(wrapper);
        List<MenuVo> menuVos = transferMenuVo(allMenu, 0L);
        return menuVos;
    }


    private List<MenuVo> transferMenuVo(List<Menu> allMenu, Long parentId) {
        List<MenuVo> resultList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(allMenu)) {
            for (Menu source : allMenu) {
                if (parentId.longValue() == source.getParentId().longValue()) {
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(source, menuVo);
                    // 递归查询子菜单 并封装消息
                    List<MenuVo> childList = transferMenuVo(allMenu, source.getId());
                    if (!childList.isEmpty()) {
                        menuVo.setChildMenu(childList);
                    }
                    resultList.add(menuVo);
                }
            }
        }
        return resultList;
    }

}
