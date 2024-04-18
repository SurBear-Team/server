package com.surbear.survey.answer.model;

import lombok.Builder;

@Builder
public record MemberAnswer(
        Long id,
        Long surveyAnswerId,
        Long surveyQuestionId,
        String answer,
        boolean deleted
) {
}
