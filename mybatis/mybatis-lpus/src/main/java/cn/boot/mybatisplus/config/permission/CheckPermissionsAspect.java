package cn.boot.mybatisplus.config.permission;

import cn.boot.mybatisplus.dao.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 17:13
 **/
@Aspect
@Component
@Slf4j
public class CheckPermissionsAspect {
    @Resource
    private MenuMapper menuMapper;

    @Pointcut("@annotation(cn.boot.mybatisplus.config.permission.CheckPermissions)")
    public void checkPermission() {
    }

    @Before("checkPermission()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        Long userId = null;
        Object[] args = joinPoint.getArgs();
        Object parObj = args[0];
        if (!Objects.isNull(parObj)) {
            Class<?> userClass = parObj.getClass();
            Field field = userClass.getDeclaredField("userId");
            field.setAccessible(true);
            userId = (Long) field.get(parObj);
        }
        //获取方法上有CheckPermissions注解的参数
        if (!Objects.isNull(userId)) {
            Class<?> clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
            if (method.getAnnotation(CheckPermissions.class) != null) {
                CheckPermissions annotation = method.getAnnotation(CheckPermissions.class);
                String menuCode = annotation.value();
                //通过用户ID、菜单编码查询是否有关联
                if (StringUtils.isNotBlank(menuCode)) {
                    int count = menuMapper.selectAuthByUserIdAndMenuCode(userId, menuCode);
                    if (count == 0) {
                        log.info("接口无访问权限");
                        throw new Exception("接口无访问权限");
                    }
//                }
                }

            }
        }
    }
}