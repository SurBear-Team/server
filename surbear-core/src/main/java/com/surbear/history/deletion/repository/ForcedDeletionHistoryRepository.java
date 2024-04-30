package com.surbear.history.deletion.repository;

import com.surbear.history.deletion.entity.ForcedDeletionHistoryEntity;
import com.surbear.history.deletion.model.ForcedDeletionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForcedDeletionHistoryRepository extends JpaRepository<ForcedDeletionHistoryEntity, Long> {

    List<ForcedDeletionHistory> findAllByDeletedSurveyIdIn(List<Long> deletedSurveyIds);
}
