package com.showtime.jkgl.mapper;

import com.showtime.jkgl.model.entity.DietStorage;
import tk.mybatis.mapper.common.Mapper;

public interface DietStorageMapper extends Mapper<DietStorage> {
    void InsertBatch(List<DietStorage> dietStorages);
}