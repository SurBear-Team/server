package com.surbear.history.deletion.model;

import lombok.Builder;

@Builder
public record ForcedDeletionHistory(
        Long id,
        Long adminMemberId,
        Long deletedSurveyId,
        boolean deleted
) {
}
