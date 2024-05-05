package com.surbear.survey.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AnswersRequest(
        List<String> answers
) {
}
