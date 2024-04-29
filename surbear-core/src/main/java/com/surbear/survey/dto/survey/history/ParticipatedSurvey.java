package com.surbear.survey.dto.survey.history;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ParticipatedSurvey(
        Long id,
        String title,
        boolean openType,
        boolean deleted,
        Instant createdAt
) {
}
