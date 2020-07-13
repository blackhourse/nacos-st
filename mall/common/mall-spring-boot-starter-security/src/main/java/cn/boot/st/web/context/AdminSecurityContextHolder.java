package cn.boot.st.web.context;

public class AdminSecurityContextHolder {

    private static final ThreadLocal<AdminSecurityContext> SECURITY_CONTEXT = new ThreadLocal<AdminSecurityContext>();

    public static void setContext(AdminSecurityContext context) {
        SECURITY_CONTEXT.set(context);
    }

    public static AdminSecurityContext getContext() {
        AdminSecurityContext context = SECURITY_CONTEXT.get();
        if (context == null) {
            context = new AdminSecurityContext();
            SECURITY_CONTEXT.set(context);
        }
        return context;
    }

    public static void clear() {
        SECURITY_CONTEXT.remove();
    }

    public static Integer getAdminId() {
        return getContext().getAdminId();
    }

    public static Integer getAccountId() {
        return getContext().getAccountId();
    }

}
