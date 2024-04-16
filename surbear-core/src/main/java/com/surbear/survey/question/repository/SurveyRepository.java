package com.surbear.survey.question.repository;

import com.surbear.survey.question.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
}
