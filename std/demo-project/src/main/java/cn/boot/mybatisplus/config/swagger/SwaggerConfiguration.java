package cn.boot.mybatisplus.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfiguration
 * @Author shiyj
 * @Description swagger配置
 * @Date 2019/11/25 13:57
 */
@EnableSwagger2
@Configuration
//@EnableKnife4j
@Profile({"dev", "default", "maht", "pre", "local"})
public class SwaggerConfiguration {
    /**
     * @return
     * @Description: swagger的配置
     */
    @Bean
    public Docket createRestApi() {
        //添加head参数start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.boot.mybatisplus.controller"))
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mybatisPlus")
                .description("mybatis-plus")
                .contact(new Contact("maht", "https://github.com/blackhourse/nacos-st.git", "285182060@qq.com   "))
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }
}
