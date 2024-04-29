package com.surbear.survey.dto.survey.history;

import lombok.Builder;

import java.time.Instant;

@Builder
public record IdAndCreatedAtForSurveyHistory(
        Long surveyId,
        Instant createdAt
) {
}
