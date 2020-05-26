package cn.wyy.service.impl;

import cn.wyy.NotFoundException;
import cn.wyy.dao.BlogDao;
import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlogById(Long id) {

        return blogDao.getBlogById(id);
    }

    @Override
    public List<Blog> getBlogsByCondition(BlogQuery blog) {
        List<Blog> bloglist = blogDao.getBlogsByCondition(blog);
        return bloglist;
    }

    @Override
    public Integer saveBlog(Blog blog) {
        Integer integer = blogDao.saveBlog(blog);
        if (integer == 0) {
            throw new NotFoundException("该博客id不存在");
        }
        return null;
    }

    @Override
    public Integer updateBlog(Blog blog) {
        Integer integer = blogDao.updateBlog(blog);
        return null;
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }
}
