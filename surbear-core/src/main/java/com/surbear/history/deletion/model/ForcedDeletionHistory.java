package com.surbear.history.deletion.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ForcedDeletionHistory(
        Long id,
        Long adminMemberId,
        Long deletedSurveyId,
        boolean deleted,
        Instant createdAt
) {
}
