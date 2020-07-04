package cn.boot.mybatisplus.config.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 17:11
 * 权限注解
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermissions {
    String value() default "";
}
