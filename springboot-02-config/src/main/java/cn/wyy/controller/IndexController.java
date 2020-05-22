package cn.wyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Enzo Cotter on 2020/4/30.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }
}
