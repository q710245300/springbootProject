package cn.wyy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Enzo Cotter on 2020/5/2.
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 这个方法设置拦截后是否登录的检测
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "没有登录，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        else {
            return true;
        }
    }
}
