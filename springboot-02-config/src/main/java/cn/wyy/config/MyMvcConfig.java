package cn.wyy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.LocaleResolver;

/**
 * Mvc的配置，相当于原来的web.xml
 *      * 比如国际化的视图解析器在这里配置后才能生效
 *      * 拦截器也需要在这里配置
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // 添加视图解析器(映射),相当于指明@RequestMapping
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 国际化的视图解析器的Bean配置，方便一会使用
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //// 添加拦截器
    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html", "/", "/user/login");
    //}
}
