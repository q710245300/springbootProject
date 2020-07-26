package cn.wyy.service.impl;

import cn.wyy.NotFoundException;
import cn.wyy.dao.BlogDao;
import cn.wyy.pojo.Blog;
import cn.wyy.pojo.BlogQuery;
import cn.wyy.pojo.User;
import cn.wyy.service.BlogService;
import cn.wyy.util.MyBeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public List<Blog> getBlogsByUser(User user) {
        return blogDao.getBlogsByUser(user);
    }

    @Transactional
    @Override
    public Blog getBlogById(Long id) {

        return blogDao.getBlogById(id);
    }


    @Transactional
    @Override
    public List<Blog> getBlogsByCondition(BlogQuery blog) {
        List<Blog> bloglist = blogDao.getBlogsByCondition(blog);
        return bloglist;
    }

    @Transactional
    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Transactional
    @Override
    public List<Blog> getRecommendedBlog(Integer size) {
        List<Blog> recommendedBlog = blogDao.getRecommendedBlog();

        recommendedBlog.sort(new Comparator<Blog>() {
            @Override
            public int compare(Blog o1, Blog o2) {
                return o2.getUpdateTime().compareTo(o1.getUpdateTime());
            }
        });
        size = Math.min(size, recommendedBlog.size());
        return recommendedBlog.subList(0, size);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogs(String query) {
        return blogDao.queryBlogs(query);
    }

    @Transactional
    @Override
    public Integer saveBlog(Blog blog) {
        // 说明是新增
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setViews(0);
            if (blog.getRecommend() == null) {
                blog.setRecommend(false);
            }
        }
        // 说明是初始化
        blog.setUpdateTime(new Date());
        Integer integer = blogDao.saveBlog(blog);
        if (integer == 0) {
            throw new NotFoundException("该博客id不存在");
        }
        return integer;
    }

    @Transactional
    @Override
    public Integer updateBlog(Blog blog) {
        Blog b = blogDao.getBlogById(blog.getId());
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }

        // 过滤掉blog中属性值为空的对象，只copy对象中有值的属性
        BeanUtils.copyProperties(blog, b, MyBeanUtil.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        Integer integer = blogDao.updateBlog(b);
        return integer;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }
}
