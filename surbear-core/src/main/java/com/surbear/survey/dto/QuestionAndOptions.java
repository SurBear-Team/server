package com.surbear.survey.dto;

import com.surbear.survey.constants.QuestionType;
import com.surbear.survey.question.model.SurveyQuestion;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionAndOptions(SurveyQuestion surveyQuestion, List<SurveyQuestionOptionsList>options) {

    public static QuestionAndOptions ofSubjectiveQuestion(SurveyQuestion surveyQuestion) {
        return new QuestionAndOptions(surveyQuestion, List.of());
    }

    public static QuestionAndOptions ofObjectiveQuestion(SurveyQuestion surveyQuestion, List<SurveyQuestionOptionsList> options) {
        return new QuestionAndOptions(surveyQuestion, List.copyOf(options));
    }
}
