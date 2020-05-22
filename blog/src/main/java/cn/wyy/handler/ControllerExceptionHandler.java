package cn.wyy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 未知错误拦截器
 */
@ControllerAdvice   // 拦截所有被@Controller注解标注的页面
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)   // 表示这个方法可用来进行异常处理(表明这个方法拦截的Exception级别)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        // 在控制台输出异常信息
        logger.error("Request URL:{}, Exception : {}", request.getRequestURL(), e);

        // 如果出现异常的页面携带状态码(404, 500)，就把这个错误页面抛出给springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");

        return mv;
    }
}
