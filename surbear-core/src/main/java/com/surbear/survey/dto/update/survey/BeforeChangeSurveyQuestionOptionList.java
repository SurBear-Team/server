package com.surbear.survey.dto.update.survey;

import lombok.Builder;

@Builder
public record BeforeChangeSurveyQuestionOptionList(
        Long id,
        String answer
) {
}
