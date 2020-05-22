package cn.wyy.service;

import cn.wyy.pojo.User;

/**
 * Created by Enzo Cotter on 2020/5/17.
 */
public interface UserSerivce {

    User checkUser(String username, String password);
}
