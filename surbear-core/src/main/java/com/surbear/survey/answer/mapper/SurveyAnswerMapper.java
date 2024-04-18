package com.surbear.survey.answer.mapper;

import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import com.surbear.survey.answer.model.SurveyAnswer;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyAnswerMapper {
    SurveyAnswerEntity toEntity(SurveyAnswer model);

    SurveyAnswer toModel(SurveyAnswerEntity entity);

}
