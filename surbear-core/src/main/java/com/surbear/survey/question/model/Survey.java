package com.surbear.survey.question.model;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import lombok.Builder;

@Builder
public record Survey(
        Long id,
        OngoingType ongoingType,
        SurveyType surveyType,
        Long surveyAuthorId,
        Integer maximumNumberOfPeople,
        String title,
        String description,
        Integer point,
        boolean openType,
        boolean deleted
) {
}
