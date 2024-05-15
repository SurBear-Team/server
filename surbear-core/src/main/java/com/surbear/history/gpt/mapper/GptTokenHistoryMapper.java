package com.surbear.history.gpt.mapper;

import com.surbear.history.gpt.entity.GptHistoryEntity;
import com.surbear.history.gpt.model.GptHistory;
import org.mapstruct.Mapper;

@Mapper
public interface GptTokenHistoryMapper {
    GptHistoryEntity toEntity(GptHistory model);

    GptHistory toModel(GptHistoryEntity entity);
}
