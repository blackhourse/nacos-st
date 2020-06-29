//package listener;
//
//import com.alibaba.nacos.api.config.ConfigType;
//import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
//import com.alibaba.nacos.spring.util.parse.DefaultYamlConfigParse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.logging.LogLevel;
//import org.springframework.boot.logging.LoggingSystem;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Properties;
//
//@Component
//@Slf4j
//public class LoggingSystemConfigListener {
//
//    /**
//     * 日志配置项的前缀
//     */
//    private static final String LOGGER_TAG = "logging.level.";
//
//    @Resource
//    private LoggingSystem loggingSystem;
//
//    @NacosConfigListener(dataId = "${spring.cloud.nacos.config.ext-config.dataId}", type = ConfigType.YAML, timeout = 5000)
//    public void onChange(String newLog) throws Exception {
//        // 使用 DefaultYamlConfigParse 工具类，解析配置
//        log.info("信息已更新[{}]",newLog);
//        Properties properties = new DefaultYamlConfigParse().parse(newLog);
//        // 遍历配置集的每个配置项，判断是否是 logging.level 配置项
//
//    }
//
//}
