package com.surbear.history.point.service;


import com.surbear.history.point.entity.PointHistoryEntity;
import com.surbear.history.point.mapper.PointHistoryMapper;
import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PointHistoryService {

    private final PointHistoryMapper pointHistoryMapper;
    private final PointHistoryRepository pointHistoryRepository;


    public PointHistory createPointHistoryBySurvey(Long memberId, Integer paidPoint) {
        return PointHistory.builder()
                .payerId(0L)
                .recipientId(memberId)
                .description("설문조사 참여")
                .paidPoint(paidPoint)
                .deleted(false)
                .build();
    }


    public Long create(PointHistory pointHistory) {
        PointHistoryEntity newEntity = pointHistoryMapper.toEntity(pointHistory);

        PointHistoryEntity savedEntity = pointHistoryRepository.save(newEntity);
        return savedEntity.getId();
    }

    public List<PointHistory> getPointHistory(Long memberId) {
        return pointHistoryRepository.findAllByRecipientId(memberId);
    }

}
