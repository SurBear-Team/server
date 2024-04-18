package com.surbear.survey.question.repository;

import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SurveyQuestionOptionRepository extends JpaRepository<SurveyQuestionOptionEntity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE SurveyQuestionOptionEntity sqoe SET sqoe.deleted = true WHERE sqoe.questionId = :questionId AND sqoe.deleted = false")
    void markDeletedByQuestionId(Long questionId);
}
