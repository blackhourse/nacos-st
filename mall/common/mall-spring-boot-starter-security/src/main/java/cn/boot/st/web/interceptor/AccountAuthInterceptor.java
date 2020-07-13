package cn.boot.st.web.interceptor;

import cn.boot.st.common.framwork.utils.HttpUtil;
import cn.boot.st.web.annotation.RequiresAuthenticate;
import cn.boot.st.web.annotation.RequiresNone;
import cn.boot.st.web.annotation.RequiresPermissions;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 是否默认要求认证
     * <p>
     * 针对 /users/** 接口，一般默认不要求认证，因为面向用户的接口，往往不需要登陆即可访问
     * 针对 /admins/** 接口，一般默认要求认证，因为面向管理员的接口，往往是内部需要更严格的安全控制
     */
    private final boolean defaultRequiresAuthenticate;


    public AccountAuthInterceptor(boolean defaultRequireAuthenticate) {
        this.defaultRequiresAuthenticate = defaultRequireAuthenticate;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 进行认证
        Integer accountId = this.obtainAccount(request);
        // 2. 进行鉴权
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 判断是否需要认证
        this.checkAuthenticate(handlerMethod, accountId);
        // 判断是否需要权限
        this.checkPermission(handlerMethod, accountId);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    private Integer obtainAccount(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request); // 获得访问令牌
        if (!StringUtils.hasText(accessToken)) { // 如果未传递，则不进行认证
            return null;
        }
        // 执行认证

        // 设置账号编号

        return null;
    }

    private void checkAuthenticate(HandlerMethod handlerMethod, Integer accountId) {
        boolean requiresAuthenticate = defaultRequiresAuthenticate;
        if (handlerMethod.hasMethodAnnotation(RequiresAuthenticate.class)
                || handlerMethod.hasMethodAnnotation(RequiresPermissions.class)) { // 如果需要权限验证，也认为需要认证
            requiresAuthenticate = true;
        } else if (handlerMethod.hasMethodAnnotation(RequiresNone.class)) {
            requiresAuthenticate = false;
        }
        if (requiresAuthenticate && accountId == null) {
            // throw 账号未登陆
        }
    }

    private void checkPermission(HandlerMethod handlerMethod, Integer accountId) {
        RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null) {
            return;
        }
        String[] permissions = requiresPermissions.value();
        if (permissions == null || permissions.length == 0) {
            return;
        }
        // 权限验证
    }


}
