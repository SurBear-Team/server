package com.surbear.survey.question.repository;

import com.surbear.survey.question.entity.SurveyQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestionEntity,Long> {
}
