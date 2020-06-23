package cn.boot.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-06-23 09:30
 **/
@RestController
@RequestMapping(value = "provider")
public class ProviderController {
    @GetMapping("test")
    public String test(String name) {
        return "provider:" + name;
    }


}
