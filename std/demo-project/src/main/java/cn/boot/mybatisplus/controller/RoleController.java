package cn.boot.mybatisplus.controller;

import cn.boot.mybatisplus.base.CommonResult;
import cn.boot.mybatisplus.config.permission.CheckPermissions;
import cn.boot.mybatisplus.dataobject.Role;
import cn.boot.mybatisplus.dto.RoleDto;
import cn.boot.mybatisplus.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.boot.mybatisplus.base.CommonResult.success;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 17:42
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @CheckPermissions(value = "roleMgr:list")
    @PostMapping("queryRole")
    @ApiOperation(value = "角色列表", response = Role.class)
    public CommonResult<List<Role>> queryRole(RoleDto roleDto) {
        List<Role> list = roleService.list();
        return success(list);
    }


}
