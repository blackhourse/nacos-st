package cn.boot.st.controller;

import cn.boot.st.cache.redis.letture.RedisService;
import cn.boot.st.common.framwork.base.Response;
import cn.boot.st.common.framwork.base.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-06 17:37
 **/

@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "set")
    @GetMapping(value = "/set")
    private ResponseData<Boolean> set(String string) {
        boolean set = redisService.set(string, string);
        return Response.ok(set);
    }

    @ApiOperation(value = "get")
    @GetMapping(value = "/get")
    private ResponseData<Object> get(String string) {
        Object o = redisService.get(string);
        return Response.ok(o);
    }
}
