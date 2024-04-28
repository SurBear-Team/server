package com.surbear.survey.dto;

import com.surbear.survey.dto.update.survey.ComparisonQuestionAndAnswer;
import com.surbear.survey.question.model.SurveyQuestion;

import java.util.List;

public record UpdateQuestionAndOptions(
        SurveyQuestion surveyQuestion,
        List<ComparisonQuestionAndAnswer> options
) {
}
