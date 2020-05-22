package cn.wyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by Enzo Cotter on 2020/5/1.
 */

@Controller
public class LoginController {

    @RequestMapping("user/login")
    // 这里的@RequestParam("username")是防止你两个名字写错了，告诉Controller这个值对应的就是前端的什么数据
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser", username);
            // 这样写可以隐藏地址中传递的username和password
            return "redirect:main.html";
        } else {
            // 告诉用户登录失败了
            model.addAttribute("msg", "用户名或者密码错误");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        // 相当于换了一个session
        session.invalidate();
        return "index";
    }
}
