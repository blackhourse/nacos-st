package cn.boot.st.config.springmvc;

import cn.boot.st.interceptor.AccessLogInterceptor;
import cn.boot.st.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Slf4j
public class CommonWebAutoConfiguration implements WebMvcConfigurer {


    @Bean
    @ConditionalOnClass(name = {"cn.boot.st.systemlog.SystemLogService", "javax.annotation.Resource"})
    @ConditionalOnMissingBean(AccessLogInterceptor.class)
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    /**
     * 重写父类提供的跨域请求处理的接口
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                // 放行哪些原始域
                .allowedOrigins("*")
                // 是否发送Cookie信息
                .allowCredentials(true)
                // 放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
                // 放行哪些原始域(头部信息)
                .allowedHeaders("*")
                // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Server", "Content-Length", "Authorization", "Access-Token", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
    }

    /**
     * 设置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry
                    .addInterceptor(accessLogInterceptor())
                    .addPathPatterns("/users/**")
                    .excludePathPatterns("/swagger-resources/**", "/doc.html", "/webjars/**", "/swagger-ui.html/**");
           /* registry.addInterceptor(this.tokenInterceptor())
                    .addPathPatterns("/user/login");
            log.info("[tokenInterceptor][加载TokenInterceptor完毕]");*/
        } catch (NoSuchBeanDefinitionException e) {
            log.warn("[addInterceptors][无法获取 AccessLogInterceptor 拦截器，因此不启动 AccessLog 的记录]");
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
