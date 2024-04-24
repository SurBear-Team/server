package com.surbear.survey.dto;

import com.surbear.survey.constants.QuestionType;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionAndOptions(Long questionId, QuestionType type, String content,
                                 boolean required, Integer page, List<String>options) {

    public static QuestionAndOptions ofSubjectiveQuestion(Long questionId, QuestionType type, String content, boolean required, Integer page) {
        return new QuestionAndOptions(questionId, type, content, required, page, List.of());
    }

    public static QuestionAndOptions ofObjectiveQuestion(Long questionId, QuestionType type, String content, boolean required, Integer page, List<String> options) {
        return new QuestionAndOptions(questionId, type, content, required, page, List.copyOf(options));
    }
}
