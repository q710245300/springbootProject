package cn.wyy.dao;

import cn.wyy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Enzo Cotter on 2020/5/17.
 */

@Repository
@Mapper
public interface UserDao {
    User findByUsernameAndPassword(String username, String password);

}
