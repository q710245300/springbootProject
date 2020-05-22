package cn.wyy.service;

import cn.wyy.pojo.Type;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
public interface TypeService {

    Integer saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllTypes();

    Integer updateType(Type type);

    void deleteType(Long id);
}
