package com.surbear.survey.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionAndOptions(Long questionId, String content, List<String> options) {

    public static QuestionAndOptions ofSubjectiveQuestion(Long questionText, String content) {
        return new QuestionAndOptions(questionText, content, List.of());
    }

    public static QuestionAndOptions ofObjectiveQuestion(Long questionText, String content, List<String> options) {
        return new QuestionAndOptions(questionText, content, List.copyOf(options));
    }
}
