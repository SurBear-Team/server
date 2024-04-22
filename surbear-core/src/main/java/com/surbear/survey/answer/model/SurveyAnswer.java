package com.surbear.survey.answer.model;

import lombok.Builder;

@Builder
public record SurveyAnswer(
        Long id,
        Long memberId,
        Long surveyId,
        boolean deleted
) {
}
