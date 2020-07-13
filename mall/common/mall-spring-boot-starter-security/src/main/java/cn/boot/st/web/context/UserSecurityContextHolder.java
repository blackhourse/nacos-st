package cn.boot.st.web.context;

/**
 * @program: mall
 * @author: maht
 * @create: 2020-07-13 18:36
 **/
public class UserSecurityContextHolder {
    private static final ThreadLocal SECURITY_CONTEXT = new ThreadLocal<UserSecurityContext>();

    public static void setSecurityContext(UserSecurityContext userSecurityContext) {
        SECURITY_CONTEXT.set(userSecurityContext);
    }
}
