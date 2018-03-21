package com.showtime.jkgl.service;

import com.showtime.jkgl.mapper.EnergyMeasurementMapper;
import com.showtime.jkgl.model.entity.DietStorage;
import com.showtime.jkgl.model.entity.EnergyMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class EnergyMeasurementService {
    
    @Autowired
    private EnergyMeasurementMapper energyMeasurementMapper;

    private final static String[] FIELD = {"measurement", "foodStorageId"};

    public void InsertBatch(List<EnergyMeasurement> energyMeasurements) {
        //验证数据库中是否已经被注册,把已经注册的删除
        for(int i=0; i<energyMeasurements.size(); ++i){
            Example example = new Example(EnergyMeasurement.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo(FIELD[0], energyMeasurements.get(i).getMeasurement());
            List<EnergyMeasurement> fromDataBase = energyMeasurementMapper.selectByExample(example);

            if(fromDataBase.size() != 0){
                energyMeasurements.remove(i);
                --i;
                continue;
            }
        }

        if(energyMeasurements.size() != 0){
            energyMeasurementMapper.InsertBatch(energyMeasurements);
        }
    }

    public List<EnergyMeasurement> getByFoodStorageId(Integer foodStorageId) {
        Example example = new Example(EnergyMeasurement.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo(FIELD[1], foodStorageId);
        return energyMeasurementMapper.selectByExample(example);
    }
}
