package cn.boot.nacos.demo.controller;

import cn.boot.nacos.demo.properties.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: boot-nacos
 * @author: maht
 * @create: 2020-06-19 11:29
 **/
@RestController
@RequestMapping(value = "config")
public class ConfigController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderProperties orderProperties;

    @Value(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;

    //        @NacosValue(value = "${order.create-frequency-seconds}")
    @Value(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    @GetMapping(value = "test")
    public String getStr() {
        logger.info("payTimeoutSeconds:" + orderProperties.getPayTimeoutSeconds());
        logger.info("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());
        String format = String.format("[payTimeoutSeconds:%s]- [createFrequencySeconds:%s]",
                orderProperties.getPayTimeoutSeconds(), orderProperties.getCreateFrequencySeconds());
        return format;
    }

    @GetMapping(value = "test2")
    public String getStr2() {
        logger.info("payTimeoutSeconds:" + payTimeoutSeconds);
        logger.info("createFrequencySeconds:" + createFrequencySeconds);
        String format = String.format("[payTimeoutSeconds:%s]- [createFrequencySeconds:%s]",
                payTimeoutSeconds, createFrequencySeconds);
        return format;
    }
}
