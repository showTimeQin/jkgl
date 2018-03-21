package com.showtime.jkgl.mapper;

import com.showtime.jkgl.model.entity.EnergyMeasurement;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EnergyMeasurementMapper extends Mapper<EnergyMeasurement> {
    void InsertBatch(List<EnergyMeasurement> energyMeasurements);
}