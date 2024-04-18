package com.surbear.survey.answer.repository;

import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswerEntity, Long> {
}
