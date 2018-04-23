package com.showtime.jkgl.mapper;

import com.showtime.jkgl.model.entity.DietStorage;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DietStorageMapper extends Mapper<DietStorage> {
    void InsertBatch(List<DietStorage> dietStorages);

    List<DietStorage> selectByIds(List<String> dietStorageIds);
}