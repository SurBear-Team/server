package com.surbear.survey.answer.model;

import lombok.Builder;

@Builder
public record ServeyAnswer(
        Long id,
        Long respondentId,
        Long serveyId,
        boolean deleted
) {
}
