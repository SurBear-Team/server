package com.surbear.survey.question.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

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
        boolean deleted,
        Instant deadLine,
        Instant createdAt

) {
}
