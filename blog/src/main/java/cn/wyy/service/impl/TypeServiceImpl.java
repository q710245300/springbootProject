package cn.wyy.service.impl;

import cn.wyy.NotFoundException;
import cn.wyy.dao.TypeDao;
import cn.wyy.pojo.Type;
import cn.wyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/18.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;



    @Transactional
    @Override
    public Type getTypeById(Long id) {
        Type typeById = typeDao.getTypeById(id);
        return typeById;
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getAllTypes(Integer size) {
        List<Type> allTypes = typeDao.getAllTypes();

        List<Type> types = allTypes;
        if (size != null) {
            allTypes.sort(new Comparator<Type>() {
                @Override
                public int compare(Type o1, Type o2) {
                    return o2.getBlogs().size() - o1.getBlogs().size();
                }
            });

            size = Math.min(size, allTypes.size());
            types = allTypes.subList(0, size);
        }

        return types;
    }

//    @Transactional
//    @Override
//    public List<Type> getTypesTop(Integer size) {
//        return typeDao.getTypesTop(size);
//    }

    @Transactional
    @Override
    public Integer saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Integer updateType(Type type) {
        Integer integer = typeDao.updateType(type);
        if (integer == 0) {
            throw new NotFoundException("修改错误，该类型的id不存在");
        }

        return integer;
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
}
