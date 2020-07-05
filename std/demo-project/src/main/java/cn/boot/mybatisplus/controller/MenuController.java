package cn.boot.mybatisplus.controller;

import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.dataobject.Menu;
import cn.boot.mybatisplus.service.MenuService;
import cn.boot.mybatisplus.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cn.boot.mybatisplus.base.CommonResult.success;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 14:10
 **/
@Api(tags = "菜单接口")
@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "添加菜单")
    @PostMapping("add")
    public CommonResult addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
        return success();
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("update")
    public CommonResult updateMenu(@RequestBody Menu menu) {
        menuService.updateMenu(menu);
        return success();
    }

    @ApiOperation(value = "删除菜单")
    @PostMapping("delete")
    public CommonResult delMenu(@RequestBody Menu menu) {
        menuService.delMenu(menu);
        return success();
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
