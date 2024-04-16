package com.surbear.survey.question.dto;

import java.util.List;

public record AnswersRequest(
        List<String> answers
) {
}
