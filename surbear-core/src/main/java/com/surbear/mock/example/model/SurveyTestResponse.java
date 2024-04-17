package com.surbear.mock.example.model;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SurveyTestResponse(
        Long id,
        OngoingType ongoingType,
        SurveyType surveyType,
        String surveyAuthorId,
        Integer maximumNumberOfPeople,
        String title,
        String description,
        Integer point,
        boolean openType,
        boolean deleted,
        Instant deadLine
) {
}
