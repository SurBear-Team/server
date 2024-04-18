package com.surbear.survey.question.mapper;

import com.surbear.survey.dto.CreateSurveyRequest;
import com.surbear.survey.dto.GetSurveyListResponse;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.model.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SurveyMapper {

    SurveyEntity toEntity(Survey model);

    Survey toModel(SurveyEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ongoingType", ignore = true)
    @Mapping(target = "point", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    SurveyEntity createSurveyRequestToEntity(CreateSurveyRequest model);

    SurveyEntity getSurveyListResponseToEntity(Survey model);
}