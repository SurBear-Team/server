package com.surbear.survey.dto;

import lombok.Builder;

@Builder
public record SurveyQuestionOptionsList(
        Long id,
        String answer
) {
}
