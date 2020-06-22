package cn.boot.nacos.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: boot-nacos
 * @author: maht
 * @create: 2020-06-19 14:28
 **/
@Component
@ConfigurationProperties(prefix = "order")
//@NacosConfigurationProperties(prefix = "order", dataId = "${nacos.config.data-id}", type = ConfigType.YAML)  注释原因是 配置文件 配置了 nacos.config.bootstrap.enable=true
@Data
public class OrderProperties {
    /**
     * 订单支付超时时长，单位：秒。
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
    private Integer createFrequencySeconds;

}
