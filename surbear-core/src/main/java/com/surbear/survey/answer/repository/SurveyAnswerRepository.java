package com.surbear.survey.answer.repository;

import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswerEntity, Long> {

    @Query("SELECT sa.id FROM SurveyAnswerEntity sa WHERE sa.memberId = :memberId AND sa.surveyId = :surveyId AND sa.deleted = false")
    Long findFirstByMemberIdAndSurveyIdAndDeletedIsFalse(Long memberId, Long surveyId);

    List<IdAndCreatedAtForSurveyHistory> findAllByMemberId(Long memberId);

    List<SurveyAnswer> findALlBySurveyIdAndDeletedIsFalse(Long surveyId);

    Integer countAllByMemberId(Long memberId);
}
