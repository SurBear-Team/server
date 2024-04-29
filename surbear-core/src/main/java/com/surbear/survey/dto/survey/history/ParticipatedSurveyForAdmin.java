package com.surbear.survey.dto.survey.history;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ParticipatedSurveyForAdmin(
        Long id,
        String title,
        String nickname,
        boolean deleted,
        Instant createdAt
) {
}
