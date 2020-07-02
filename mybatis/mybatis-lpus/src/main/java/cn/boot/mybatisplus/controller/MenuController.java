package cn.boot.mybatisplus.controller;

import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.dataobject.Menu;
import cn.boot.mybatisplus.service.MenuService;
import cn.boot.mybatisplus.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.boot.mybatisplus.base.CommonResult.success;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 14:10
 **/
@Api(value = "菜单接口")
@RestController
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "添加菜单", produces = "application/json")
    @PostMapping("add")
    public CommonResult addMenu(Menu menu) {
        menuService.addMenu(menu);
        return success("200");
    }

    @ApiOperation(value = "菜单列表", response = MenuVo.class)
    @GetMapping("list")
    public CommonResult<List<MenuVo>> addMenu() {
        return success(menuService.queryMenuTree());
    }

    @ApiOperation(value = "根据userId获取菜单列表", response = MenuVo.class)
    @PostMapping("queryMenus")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "Long")
    )
    public CommonResult<List<MenuVo>> addMenu(Long userId) {
        return success(menuService.queryMenus(userId));
    }
}
