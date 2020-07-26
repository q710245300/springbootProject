package cn.wyy.controller;

import cn.wyy.NotFoundException;
import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.User;
import cn.wyy.service.BlogService;
import cn.wyy.service.TypeService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/10.
 */
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    // 这样写就只会拦截localhost:8080/id/name这种形式的url
    @GetMapping()
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> blogList = blogService.getAllBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);

        model.addAttribute("types", typeService.getAllTypes(6));

        model.addAttribute("recommendBlogs", blogService.getRecommendedBlog(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam String query, Model model) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> blogList = blogService.queryBlogs("%" + query + "%");
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogList", pageInfo);
        model.addAttribute("query", query);

        return "search";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
