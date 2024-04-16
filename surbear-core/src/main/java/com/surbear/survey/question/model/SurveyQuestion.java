package com.surbear.survey.question.model;

import com.surbear.survey.constants.QuestionType;

public record SurveyQuestion(
        Long id,
        Long surveyId,
        QuestionType questionType,
        String content,
        Integer page,
        Integer maxText,
        boolean required,
        boolean deleted

) {
}
