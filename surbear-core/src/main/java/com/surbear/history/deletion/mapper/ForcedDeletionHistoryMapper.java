package com.surbear.history.deletion.mapper;

import com.surbear.history.deletion.entity.ForcedDeletionHistoryEntity;
import com.surbear.history.deletion.model.ForcedDeletionHistory;
import org.mapstruct.Mapper;

@Mapper
public interface ForcedDeletionHistoryMapper {
    ForcedDeletionHistoryEntity toEntity(ForcedDeletionHistory model);

    ForcedDeletionHistory toModel(ForcedDeletionHistoryEntity entity);
}
