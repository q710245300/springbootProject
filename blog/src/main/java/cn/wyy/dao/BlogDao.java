package cn.wyy.dao;

import cn.wyy.pojo.Blog;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
public interface BlogDao {

    Blog getBlogById(Long id);

    List<Blog> getBlogsByCondition(Blog blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);
}
