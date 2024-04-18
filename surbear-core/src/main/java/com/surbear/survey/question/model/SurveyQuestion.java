package com.surbear.survey.question.model;

import com.surbear.survey.constants.QuestionType;
import lombok.Builder;

@Builder
public record SurveyQuestion(
        Long id,
        Long surveyId,
        QuestionType questionType,
        String content,
        Integer page,
        Integer questionOrder,
        Integer maxText,
        boolean required,
        boolean deleted

) {
}
