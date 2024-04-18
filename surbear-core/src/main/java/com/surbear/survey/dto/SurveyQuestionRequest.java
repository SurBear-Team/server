package com.surbear.survey.dto;

import com.surbear.survey.question.model.SurveyQuestion;
import com.surbear.survey.question.model.SurveyQuestionOption;
import lombok.Builder;

import java.util.List;

@Builder
public record SurveyQuestionRequest(
        List<String> answers,
        SurveyQuestion surveyQuestion
) {
}
