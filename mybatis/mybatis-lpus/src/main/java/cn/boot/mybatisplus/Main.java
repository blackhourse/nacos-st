package cn.boot.mybatisplus;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 10:19
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "cn.boot.mybatisplus.dao")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
