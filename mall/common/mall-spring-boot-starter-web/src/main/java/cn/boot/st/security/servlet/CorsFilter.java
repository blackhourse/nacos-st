package cn.boot.st.security.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cors 过滤器
 * <p>
 * 未来使用 {@link org.springframework.web.filter.CorsFilter} 替换
 */
@Slf4j
@Component
public class CorsFilter implements Filter {

    public void init(FilterConfig filterConfig) {
        log.info("[过滤器启动。。。。]");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Max-Age", "1800");
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        // 例如说，vue axios 请求时，会自带该逻辑的
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        // 如果是其它请求方法，则继续过滤器。
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
