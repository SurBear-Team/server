package com.surbear.mock.example.model;

import com.surbear.survey.model.SurveyType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SurveyTestResponse(
        Long id,
        String title,
        String author,
        Integer point,
        SurveyType type,
        Instant deadLine
) {
}
