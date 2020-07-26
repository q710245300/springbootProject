package cn.wyy.service.impl;

import cn.wyy.dao.UserDao;
import cn.wyy.pojo.User;
import cn.wyy.service.EmailService;
import cn.wyy.service.UserSerivce;
import cn.wyy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Enzo Cotter on 2020/5/17.
 */

@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationEventPublisher publisher;


    @Transactional
    @Override
    public User checkUser(String username, String password) {

        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));

        publisher.publishEvent(user.getEmail());

        return user;
    }
}
