package com.surbear.survey.question.model;

import com.surbear.survey.constants.QuestionType;
import lombok.Builder;

@Builder
public record SurveyQuestionOption(
        Long id,
        Long questionId,
        String answer,
        boolean deleted

) {
}
