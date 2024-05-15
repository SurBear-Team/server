package com.surbear.history.gpt.service;

import com.surbear.history.gpt.entity.GptHistoryEntity;
import com.surbear.history.gpt.mapper.GptTokenHistoryMapper;
import com.surbear.history.gpt.model.GptHistory;
import com.surbear.history.gpt.repository.GptTokenHistoryRepository;
import com.surbear.member.entity.RoleEntity;
import com.surbear.member.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GptTokenHistoryService {

    private final GptTokenHistoryMapper gptTokenHistoryMapper;
    private final GptTokenHistoryRepository gptTokenHistoryRepository;

    @Transactional
    public Long create(GptHistory gptHistory) {
        GptHistoryEntity gptHistoryEntity = gptTokenHistoryMapper.toEntity(gptHistory);

        GptHistoryEntity savedEntity = gptTokenHistoryRepository.save(gptHistoryEntity);
        return savedEntity.getId();
    }

    public GptHistory getLast(){
        GptHistoryEntity newEntity = gptTokenHistoryRepository.findTopByOrderByCreatedAtDesc();
        return gptTokenHistoryMapper.toModel(newEntity);
    }


}
