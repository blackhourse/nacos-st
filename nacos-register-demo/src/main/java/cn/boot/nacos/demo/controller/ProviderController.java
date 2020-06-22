package cn.boot.nacos.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-06-22 14:02
 **/
@RestController
@RequestMapping(value = "provider")
public class ProviderController {

    @GetMapping("demo")
    public String test() {
        return "hello";
    }

}
