package com.surbear.history.point.repository;

import com.surbear.history.point.entity.PointHistoryEntity;
import com.surbear.history.point.model.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {

    List<PointHistory> findAllByRecipientId(Long recipientId);
    PointHistoryEntity findByIdAndDeletedIsFalse(Long id);
}
