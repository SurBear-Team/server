package com.surbear.survey.question.model;

import com.surbear.survey.constants.QuestionType;

public record SurveyQuestionOption(
        Long id,
        Long questionId,
        String answer,
        QuestionType questionType,
        boolean deleted

) {
}
