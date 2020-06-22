package cn.boot.nacos.demo.properties;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-06-19 15:37
 * // 因为我们要使用自动刷新配置的功能，必须使用 @NacosConfigurationProperties 注解。
 **/
@Component
@NacosConfigurationProperties(prefix = "", dataId = "${nacos.config.data-id}", type = ConfigType.YAML, autoRefreshed = true)
@Data
public class TestProperties {
    private String test;
    private String cc;
}
