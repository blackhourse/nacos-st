package cn.boot.st.controller;

import cn.boot.st.common.framwork.vo.CommonResult;
import cn.boot.st.dao.UserMapper;
import cn.boot.st.dataobject.User;
import cn.boot.st.jwt.JwtIgnore;
import cn.boot.st.jwt.JwtTokenUtil;
import cn.boot.st.jwt.UserToken;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-06 17:37
 **/

@Api(tags = "登录")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @JwtIgnore
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public CommonResult login(@RequestBody User userDto, HttpServletResponse response) {
        //...参数合法性验证
        //从数据库获取用户信息
        User user = userMapper.selectById(userDto.getId());
        //....用户、密码验证
        //创建token，并将token放在响应头
        UserToken userToken = new UserToken();
        BeanUtils.copyProperties(user, userToken);
        String token = JwtTokenUtil.createToken(JSONObject.toJSONString(userToken));
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
        //定义返回结果
        userToken.setToken(token);
        return CommonResult.success(userToken);
    }
}
