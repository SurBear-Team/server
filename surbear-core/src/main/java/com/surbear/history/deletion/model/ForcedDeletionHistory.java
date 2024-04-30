package com.surbear.history.deletion.model;

public record ForcedDeletionHistory(
        Long id,
        Long adminMemberId,
        Long deletedSurveyId,
        boolean deleted
) {
}
