package com.surbear.report.model;

public record SurveyReport(
        Long id,
        Long reporterId,
        Long surveyId,
        String reason,
        boolean deleted
) {
}
