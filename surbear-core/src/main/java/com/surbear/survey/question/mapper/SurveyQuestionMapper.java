package com.surbear.survey.question.mapper;

import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.model.SurveyQuestion;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyQuestionMapper {
    SurveyQuestionEntity toEntity(SurveyQuestion model);

    SurveyQuestion toModel(SurveyQuestionEntity entity);
}
