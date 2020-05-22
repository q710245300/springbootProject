package cn.wyy.dao;

import cn.wyy.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
@Mapper
@Repository
public interface TypeDao{

    Integer saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllTypes();

    Integer updateType(Type type);

    void deleteType(Long id);


}
