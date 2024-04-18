package com.surbear.survey.dto;

import com.surbear.survey.constants.SurveyType;

import java.time.Instant;

public record UpdateSurveyRequest(
        String title,
        String description,
        SurveyType surveyType,
        boolean openType,
        Integer maximumNumberOfPeople,
        Instant deadLine
) {
}
