package cn.wyy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
public class Logininterceptor implements HandlerInterceptor {

    // 请求来的时候拦截并做一个预处理
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
        }
        // 放行
        return true;
    }
}
