package com.surbear.history.gpt.repository;

import com.surbear.history.gpt.entity.GptHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GptTokenHistoryRepository extends JpaRepository<GptHistoryEntity, Long> {
}
