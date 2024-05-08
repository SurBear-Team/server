package com.surbear.history.point.mapper;

import com.surbear.history.point.entity.PointHistoryEntity;
import com.surbear.history.point.model.PointHistory;
import org.mapstruct.Mapper;

@Mapper
public interface PointHistoryMapper {
    PointHistoryEntity toEntity(PointHistory model);

    PointHistory toModel(PointHistoryEntity entity);
}
