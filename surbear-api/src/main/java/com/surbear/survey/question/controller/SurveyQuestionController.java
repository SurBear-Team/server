package com.surbear.survey.question.controller;


import com.surbear.configuration.authorization.Authorization;
import com.surbear.survey.dto.CreateSurveyRequest;
import com.surbear.survey.dto.SurveyQuestionRequest;
import com.surbear.survey.dto.UpdateQuestionAndOptions;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.service.SurveyQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "설문 생성", description = "설문 생성 관련 API")
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyQuestionController {

    private final SurveyQuestionService service;

    @Operation(summary = "새 설문 만들기", description = "새 설문 만들기에 해당하는 API")
    @PostMapping("")
    public Long createSurvey(@RequestBody CreateSurveyRequest req,
                             @Authorization
                             @Parameter(hidden = true)
                             Long memberId
    ) {
        return service.createSurvey(req, memberId);
    }

    @Operation(summary = "새 질문 만들기", description = "새 질문 만들기에 해당하는 API, 바디에 DTO는 두개의 객체타입이니 확인하자")
    @PostMapping("/question")
    public Long createQuestion(@RequestBody SurveyQuestionRequest req) {
        return service.createQuestion(req.surveyQuestion(), req.answers());
    }

    @Operation(summary = "설문의 질문 & 선택지 수정", description = "주어진 dto를 보고 설문의 질문과 선택지를 수정한다")
    @PostMapping("/question-options")
    public boolean getSurveyByCreatedAt(@RequestBody UpdateQuestionAndOptions updateQuestionAndOptions) {
        return service.updateSurveyQuestionAndOptions(updateQuestionAndOptions.surveyQuestion(), updateQuestionAndOptions.options());
    }
}
