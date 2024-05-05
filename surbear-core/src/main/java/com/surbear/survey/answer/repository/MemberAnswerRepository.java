package com.surbear.survey.answer.repository;

import com.surbear.survey.answer.entity.MemberAnswerEntity;
import com.surbear.survey.answer.model.MemberAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAnswerRepository extends JpaRepository<MemberAnswerEntity, Long> {

    @Query("SELECT m.answer FROM MemberAnswerEntity m WHERE m.surveyQuestionId = :surveyQuestionId AND m.surveyAnswerId = :surveyAnswerId AND m.deleted = false")
    List<String> findAnswersBySurveyQuestionIdAndSurveyAnswerId(Long surveyQuestionId, Long surveyAnswerId);

    List<MemberAnswer> findAllBySurveyAnswerIdAndDeletedIsFalse(Long surveyAnswerId);
}
