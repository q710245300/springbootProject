package cn.wyy.service.impl;

import cn.wyy.NotFoundException;
import cn.wyy.dao.TypeDao;
import cn.wyy.pojo.Type;
import cn.wyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getAllTypes() {
        List<Type> allTypes = typeDao.getAllTypes();
        return allTypes;
    }

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
