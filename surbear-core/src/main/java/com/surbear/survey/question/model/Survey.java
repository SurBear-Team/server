package com.surbear.survey.question.model;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;

public record Survey(
        Long id,
        OngoingType ongoingType,
        SurveyType surveyType,
        Long surveyAuthorId,
        String title,
        String description,
        Integer point,
        boolean deleted
) {
}
