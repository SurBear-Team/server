package com.surbear.survey.dto;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import lombok.Builder;

import java.time.Instant;

public record GetSurveyListResponse(
        Long id,
        OngoingType ongoingType,
        SurveyType surveyType,
        String title
) {
}

