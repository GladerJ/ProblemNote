package top.mygld.problemnote.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // 放行登录、注册、验证码、静态资源、API
        if (uri.equals("/login") ||
                uri.equals("/login/") ||
                uri.equals("/logout") ||
                uri.equals("/register") ||
                uri.equals("/captcha") ||  // 添加这一行
                uri.startsWith("/api") ||
                uri.startsWith("/static") ||
                uri.startsWith("/css") ||
                uri.startsWith("/js") ||
                uri.startsWith("/favicon")) {
            return true;
        }

        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}