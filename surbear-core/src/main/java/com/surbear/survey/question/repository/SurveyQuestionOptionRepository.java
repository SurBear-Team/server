package com.surbear.survey.question.repository;

import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionOptionRepository extends JpaRepository<SurveyQuestionOptionEntity,Long> {
}
