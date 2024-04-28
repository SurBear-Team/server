package com.surbear.survey.dto.update.survey;

public record AfterChangeSurveyQuestionOptionList(
        Long id,
        String answer,
        boolean deleteFlag,
        boolean creationFlag
) {
}
