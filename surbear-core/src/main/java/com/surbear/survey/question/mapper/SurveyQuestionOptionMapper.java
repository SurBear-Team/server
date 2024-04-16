package com.surbear.survey.question.mapper;

import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import com.surbear.survey.question.model.SurveyQuestionOption;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyQuestionOptionMapper {
    SurveyQuestionOptionEntity toEntity(SurveyQuestionOption model);

    SurveyQuestionOption toModel(SurveyQuestionOptionEntity entity);
}
