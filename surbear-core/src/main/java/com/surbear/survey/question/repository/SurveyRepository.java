package com.surbear.survey.question.repository;

import com.surbear.survey.dto.GetSurveyListResponse;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
    List<GetSurveyListResponse> findBySurveyAuthorIdAndDeletedFalse(Long surveyAuthorId);
}
