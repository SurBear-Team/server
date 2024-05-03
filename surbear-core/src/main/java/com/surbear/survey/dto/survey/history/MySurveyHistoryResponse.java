package com.surbear.survey.dto.survey.history;

import com.surbear.survey.constants.OngoingType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record MySurveyHistoryResponse(
        Long id,
        String title,
        OngoingType ongoingType,
        Instant startDate,
        boolean deleted
) {
}
