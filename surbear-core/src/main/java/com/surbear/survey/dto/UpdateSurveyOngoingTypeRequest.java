package com.surbear.survey.dto;

import com.surbear.survey.constants.OngoingType;

public record UpdateSurveyOngoingTypeRequest(
        Long id,
        OngoingType type
) {
}
