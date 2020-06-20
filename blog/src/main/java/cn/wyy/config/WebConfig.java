package cn.wyy.config;

import cn.wyy.interceptor.Logininterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.rmi.runtime.Log;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加入自定义的interceptor,需要过滤/admin下的页面，同时不需要过滤掉登录页面/admin和登录请求url/admin/login
        registry.addInterceptor(new Logininterceptor())
                .addPathPatterns("/admin/**").excludePathPatterns("/admin").excludePathPatterns("/admin/login");
    }
}
