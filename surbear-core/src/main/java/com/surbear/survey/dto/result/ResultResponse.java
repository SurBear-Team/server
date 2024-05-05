package com.surbear.survey.dto.result;

import com.surbear.survey.constants.QuestionType;
import com.surbear.survey.dto.AnswersRequest;
import lombok.Builder;

@Builder
public record ResultResponse(
        Long questionId,
        QuestionType questionType,
        AnswersRequest request
) {
}
