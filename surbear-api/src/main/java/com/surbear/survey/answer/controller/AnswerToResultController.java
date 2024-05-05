package com.surbear.survey.answer.controller;

import com.surbear.survey.answer.service.AnswerToResultService;
import com.surbear.survey.dto.result.FinalResultRequest;
import com.surbear.survey.dto.result.FinalResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "설문 결과", description = "설문 결과 관련 API")
@RequiredArgsConstructor
@RequestMapping("/survey/result")
public class AnswerToResultController {

    private final AnswerToResultService answerToResultService;

    @Operation(summary = "답변결과 반환", description = "설문 id와 리스트형태의 질문id를 입력받는다.")
    @PostMapping("")
    public List<FinalResultResponse> getFinalSurveyResult(@RequestBody FinalResultRequest req) {
        return answerToResultService.getFinalSurveyResult(req.id(),req.questIdList());
    }

}
