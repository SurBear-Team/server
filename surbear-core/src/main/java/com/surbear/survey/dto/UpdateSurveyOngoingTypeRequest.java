package com.surbear.survey.dto;

import com.surbear.survey.constants.OngoingType;
import lombok.Builder;

@Builder
public record UpdateSurveyOngoingTypeRequest(
        Long id,
        OngoingType type
) {
}
