package com.surbear.survey.question.mapper;

import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.model.Survey;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyMapper {

    SurveyEntity toEntity(Survey model);

    Survey toModel(SurveyEntity entity);
}
