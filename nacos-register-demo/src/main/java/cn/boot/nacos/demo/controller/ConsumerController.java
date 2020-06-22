package cn.boot.nacos.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-06-22 14:04
 **/
@RestController
@RequestMapping(value = "consumer")
public class ConsumerController {

    @NacosInjected
    NamingService namingService;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/demo")
    public String consumer() throws IllegalStateException, NacosException {
        // 获得实例
        Instance instance = null;
        if (false) {
            List<Instance> instances = namingService.getAllInstances("demo-application");
            // 获得首个实例，进行调用
            instance = instances.stream().findFirst()
                    .orElseThrow(() -> new IllegalStateException("未找到对应的 Instance"));
        } else {
            instance = namingService.selectOneHealthyInstance("demo-application");
        }
        // 执行请求
        return restTemplate.getForObject("http://" + instance.toInetAddr() + "/provider/demo",
                String.class);
    }

}
