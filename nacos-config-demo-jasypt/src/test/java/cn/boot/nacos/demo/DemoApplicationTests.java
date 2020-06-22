package cn.boot.nacos.demo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encode() {
        String applicationName = "demo-application";
        System.out.println(encryptor.encrypt(applicationName));
    }

//    @Value("${spring.application.name}")
    @NacosValue("${spring.application.name}")
    private String applicationName;

    @Test
    public void print() {
        System.out.println(applicationName);
    }
}
