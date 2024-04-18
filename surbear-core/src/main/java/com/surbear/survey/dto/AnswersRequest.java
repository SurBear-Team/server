package com.surbear.survey.dto;

import java.util.List;

public record AnswersRequest(
        List<String> answers
) {
}
