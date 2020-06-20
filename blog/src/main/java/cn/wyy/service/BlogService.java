package cn.wyy.service;

import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.User;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
public interface BlogService {

    Blog getBlogById(Long id);

    List<Blog> getBlogsByUser(User user);

    List<Blog> getBlogsByCondition(BlogQuery blog);

    Integer saveBlog(Blog blog);

    Integer updateBlog(Blog blog);

    void deleteBlog(Long id);
}
