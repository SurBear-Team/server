package com.surbear.survey.question.controller;


import com.surbear.survey.question.dto.SurveyQuestionRequest;
import com.surbear.survey.question.service.SurveyQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "설문 질문생성", description = "설문진문 생성 관련 API")
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyQuestionController {

    private final SurveyQuestionService service;

    @Operation(summary = "새 질문 만들기", description = "새 질문 만들기에 해당하는 API")
    @PostMapping("/question")
    public Long create(@RequestBody SurveyQuestionRequest req) {
        return service.createQuestion(req.surveyQuestion(), req.answersRequest());
    }
}
