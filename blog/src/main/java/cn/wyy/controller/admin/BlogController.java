package cn.wyy.controller.admin;

import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.Type;
import cn.wyy.pojo.User;
import cn.wyy.service.BlogService;
import cn.wyy.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, BlogQuery blog, Model model, HttpSession session) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        User loginUser = (User) session.getAttribute("user");
        List<Blog> blogList = blogService.getBlogsByUser(loginUser);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);
        model.addAttribute("types", typeService.getAllTypes(null));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, BlogQuery blog, Model model) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> blogList = blogService.getBlogsByCondition(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);
        return "admin/blogs :: blogSearchResult";
    }

    // 跳转到新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.getAllTypes(null));
        return "admin/blogs-input";
    }

    // 跳转到修改blog页面
    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.getAllTypes(null));
        model.addAttribute("blog", blogService.getBlogById(id));
        return "admin/blogs-input";
    }

    // 提交新增blog请求
    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        Integer integer;
        if (blog.getId() == null) {
            integer = blogService.saveBlog(blog);
        }
        else {
            integer  = blogService.updateBlog(blog);
        }
        if (integer == 0) {
            // 新增失败
            attributes.addFlashAttribute("message", "操作失败");
        }
        else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }



//    // 提交修改blog请求
//    @PostMapping("/blogs/{id}")
//    public String editPost(Blog blog, RedirectAttributes attributes) {
//        int b = blogService.updateBlog(blog);
//        if(b ==0){
//            attributes.addFlashAttribute("message", "修改失败");
//        }else {
//            attributes.addFlashAttribute("message", "修改成功");
//        }
//        return "redirect:/admin/blogs";
//    }

    // 删除blog请求
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
