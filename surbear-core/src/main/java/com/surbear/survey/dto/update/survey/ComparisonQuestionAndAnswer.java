package com.surbear.survey.dto.update.survey;


public record ComparisonQuestionAndAnswer(

        BeforeChangeSurveyQuestionOptionList beforeChangeSurveyQuestionOptionList,
        AfterChangeSurveyQuestionOptionList afterChangeSurveyQuestionOptionList
) {
}
