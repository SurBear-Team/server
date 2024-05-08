package com.surbear.history.point.repository;

import com.surbear.history.point.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {
}
