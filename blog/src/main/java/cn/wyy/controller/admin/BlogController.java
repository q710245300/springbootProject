package cn.wyy.controller.admin;

import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.Type;
import cn.wyy.service.BlogService;
import cn.wyy.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String blogs(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, BlogQuery blog, Model model) {
        String orderBy = "updateTime" + "desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> blogList = blogService.getBlogsByCondition(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);
        List<Type> allTypes = typeService.getAllTypes();
        model.addAttribute("types", allTypes);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, BlogQuery blog, Model model) {
        String orderBy = "updateTime" + "desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> blogList = blogService.getBlogsByCondition(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);
        return "admin/blogs :: blogSearchResult";
    }
}
