package com.surbear.survey.dto;

import com.surbear.survey.question.model.SurveyQuestion;
import lombok.Builder;

@Builder
public record SurveyQuestionRequest(
        AnswersRequest answersRequest,
        SurveyQuestion surveyQuestion
) {
}
