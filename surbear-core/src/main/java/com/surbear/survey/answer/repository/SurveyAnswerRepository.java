package com.surbear.survey.answer.repository;

import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswerEntity, Long> {

    @Query("SELECT sa.id FROM SurveyAnswerEntity sa WHERE sa.memberId = :memberId AND sa.surveyId = :surveyId AND sa.deleted = false")
    Long findFirstByMemberIdAndSurveyIdAndDeletedIsFalse(Long memberId, Long surveyId);

}
