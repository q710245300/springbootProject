package cn.wyy.controller.admin;

import cn.wyy.pojo.Blog;
import cn.wyy.pojo.User;
import cn.wyy.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by Enzo Cotter on 2020/5/17.
 */

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserSerivce userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    // 首次登陆
    @PostMapping("/login")
    public String loginPage(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);

        if (user != null) {
            // 不要把密码放到session中，很不安全
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }
        else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    // 这里有个问题，就是用户直接访问/admin/login的时候会报错
//    @GetMapping
//    public String loginPage2(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            return "admin/index";
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
