package com.showtime.jkgl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.showtime.jkgl.mapper.DietStorageMapper;
import com.showtime.jkgl.model.entity.DietStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class DietStorageService {

    @Autowired
    private DietStorageMapper dietStorageMapper;

    private final static String[] FIELD = {"name", "classification"};

    public void InsertBatch(List<DietStorage> dietStorages) {
        //验证数据库中是否已经被注册,把已经注册的删除
        for(int i=0; i<dietStorages.size(); ++i){
            Example example = new Example(DietStorage.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo(FIELD[0], dietStorages.get(i).getName());
            List<DietStorage> fromDataBase = dietStorageMapper.selectByExample(example);

            if(fromDataBase.size() != 0){
                dietStorages.remove(i);
                --i;
                continue;
            }
        }

        if(dietStorages.size() != 0){
            dietStorageMapper.InsertBatch(dietStorages);
        }
    }

    public Page<DietStorage> getDietStorageByClassification(String classification, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(DietStorage.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo(FIELD[1], classification);
        List<DietStorage> fromDataBase = dietStorageMapper.selectByExample(example);
        return (Page<DietStorage>)fromDataBase;
    }

    public DietStorage getDietStorageBySpell(String spell){
        DietStorage dietStorage = new DietStorage();
        dietStorage.setSpell(spell);
        return dietStorageMapper.selectOne(dietStorage);
    }

    public void update(String name, DietStorage dietStorage, Object value, Class clazz) throws Exception{
        String method = "set" + name.substring(0,1).toUpperCase() + name.substring(1);

        if(clazz.equals(String.class)){
            Method setMethod = DietStorage.class.getDeclaredMethod(method, String.class);
            setMethod.invoke(dietStorage,(String)value);
            log.info("ok -- String");
        }else if(clazz.equals(BigDecimal.class)){
            Method setMethod = DietStorage.class.getDeclaredMethod(method, BigDecimal.class);
            setMethod.invoke(dietStorage,(BigDecimal)value);
            log.info("ok -- BigDecimal");
        }

        log.info(dietStorage.toString());
        dietStorageMapper.updateByPrimaryKeySelective(dietStorage);
    }

    public void update(String name, BigDecimal value) throws Exception{
        updateDetail(name, value);
    }

    private void updateDetail(String name, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    }
}
