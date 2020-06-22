package cn.boot.nacos.demo.test;

import cn.boot.nacos.demo.properties.TestProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-06-19 15:40
 **/
@RestController
@RequestMapping("demo")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestProperties testProperties;


    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }


    @GetMapping("/test_properties")
    public TestProperties testProperties() {
        return testProperties;
    }


    @GetMapping("/logger")
    public void logger() {
        logger.info("[logger][info][测试一下]");
        logger.debug("[logger][debug][测试一下]");
    }
}
