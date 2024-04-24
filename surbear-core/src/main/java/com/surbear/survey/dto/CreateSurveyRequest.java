package com.surbear.survey.dto;

import com.surbear.survey.constants.SurveyType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record CreateSurveyRequest(
        SurveyType surveyType,
        Long surveyAuthorId,
        Integer maximumNumberOfPeople,
        String title,
        String description,
        boolean openType,
        Instant deadLine

        ) {
}
