package com.surbear.survey.dto.update.survey;

import com.surbear.survey.dto.SurveyQuestionOptionsList;
import com.surbear.survey.dto.update.survey.BeforeChangeSurveyQuestionOptionList;

public record ComparisonQuestionAndAnswer(
        BeforeChangeSurveyQuestionOptionList beforeChangeSurveyQuestionOptionList,
        AfterChangeSurveyQuestionOptionList afterChangeSurveyQuestionOptionList
) {
}
