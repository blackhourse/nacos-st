package cn.boot.st.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 简单的 Swagger2 自动配置类
 * <p>
 * 较为完善的，可以了解 https://mvnrepository.com/artifact/com.spring4all/spring-boot-starter-swagger
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "swagger", value = "enable", matchIfMissing = true)
// 允许使用 swagger.enable=false 禁用 Swagger
@Profile({"dev", "test"})
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }


    /**
     * 前台API分组
     *
     * @return
     */

    @Bean(value = "indexApi")
    public Docket indexApi() {
        //添加head参数start
////        ParameterBuilder tokenPar = new ParameterBuilder();
////        List<Parameter> pars = new ArrayList<Parameter>();
////        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
////        pars.add(tokenPar.build());
        SwaggerProperties properties = swaggerProperties();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前台API分组")
                .apiInfo(apiInfo(properties))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.boot.st.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 后台API分组
     *
     * @return
     */

    @Bean(value = "adminApi")
    public Docket adminApi() {
        SwaggerProperties properties = swaggerProperties();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台API分组")
                .apiInfo(apiInfo(properties))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.boot.st.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public Docket createRestApi() {
        SwaggerProperties properties = swaggerProperties();
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(properties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .build();
    }

}
