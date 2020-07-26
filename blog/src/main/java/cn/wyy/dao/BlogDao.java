package cn.wyy.dao;

import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
@Mapper
@Repository
public interface BlogDao {

    Blog getBlogById(Long id);

    List<Blog> getBlogsByUser(User user);

    List<Blog> getAllBlogs();

    List<Blog> getBlogsByCondition(BlogQuery blog);

    List<Blog> getRecommendedBlog();

    List<Blog> queryBlogs(String query);

    Integer saveBlog(Blog blog);

    Integer updateBlog(Blog blog);

    void deleteBlog(Long id);
}
