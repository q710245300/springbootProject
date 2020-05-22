package cn.wyy.controller;

import cn.wyy.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Enzo Cotter on 2020/5/10.
 */
@Controller
public class IndexController {

    // 这样写就只会拦截localhost:8080/id/name这种形式的url
    @GetMapping()
    public String index() {
        //int i = 9 / 0;
        //String blog = null;
        //if (blog == null) {
        //    throw new NotFoundException("博客不存在");
        //}
        System.out.println("------------index方法-------------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
