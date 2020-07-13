package cn.boot.st.web.annotation;

import java.lang.annotation.*;

/**
 * 通过将该注解添加到 Controller 的方法上，声明无需进行登陆
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresNone {
}
