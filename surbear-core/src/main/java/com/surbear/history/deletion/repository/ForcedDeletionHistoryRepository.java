package com.surbear.history.deletion.repository;

import com.surbear.history.deletion.entity.ForcedDeletionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForcedDeletionHistoryRepository extends JpaRepository<ForcedDeletionHistoryEntity, Long> {
}
