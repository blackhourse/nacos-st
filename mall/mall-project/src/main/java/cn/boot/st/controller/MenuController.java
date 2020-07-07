package cn.boot.st.controller;

import cn.boot.st.common.framwork.base.Response;
import cn.boot.st.common.framwork.base.ResponseCode;
import cn.boot.st.common.framwork.base.ResponseData;
import cn.boot.st.common.framwork.exception.ServiceException;
import cn.boot.st.dataobject.Menu;
import cn.boot.st.service.MenuService;
import cn.boot.st.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 14:10
 **/
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/users/menu")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "添加菜单")
    @PostMapping("add")
    public ResponseData addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
        return Response.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("update")
    public ResponseData updateMenu(@RequestBody Menu menu) {
        menuService.updateMenu(menu);
        return Response.ok();
    }

    @ApiOperation(value = "删除菜单")
    @PostMapping("delete")
    public ResponseData delMenu(@RequestBody Menu menu) {
        menuService.delMenu(menu);
        return Response.ok();
    }

    @ApiOperation(value = "菜单列表", response = MenuVo.class)
    @GetMapping("list")
    public ResponseData<List<MenuVo>> menuList() throws Exception {
        if (1 == 1) {
            throw new ServiceException(ResponseCode.USER_EXCEPTION_CODE);
        }
        return Response.ok(menuService.queryMenuTree());
    }

    @ApiOperation(value = "根据userId获取菜单列表", response = MenuVo.class)
    @PostMapping("queryMenus")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "Long")
    )
    public ResponseData<List<MenuVo>> queryMenus(Long userId) {
        return Response.ok(menuService.queryMenus(userId));
    }
}
