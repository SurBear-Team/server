package com.surbear.survey.question.controller;


import com.surbear.survey.dto.UpdateSurveyRequest;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.service.SurveyManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "설문 관리", description = "설문관리 관련 API")
@RequiredArgsConstructor
@RequestMapping("/survey/management")
public class SurveyManagementController {

    private final SurveyManagementService service;

    @Operation(summary = "내설문 뷰 설문 리스트 조회", description = "사용자 id기반 설문 리스트 조회")
    @GetMapping("/list/{surveyAuthorId}")
    public List<Survey> getSurveyList(@PathVariable Long surveyAuthorId) {
        return service.getSurveyList(surveyAuthorId);
    }

    @Operation(summary = "내설문 단일 설문지 조회", description = "사용자 id기반 설문 조회")
    @GetMapping("{surveyId}")
    public Survey getSurvey(@PathVariable Long surveyId) {
        return service.getSurvey(surveyId);
    }

    @Operation(summary = "내설문 단일 업데이트", description = "사용자 id와 입력하게 되는 수정값 기반 업데이트")
    @PutMapping("{surveyId}")
    public int updateSurvey(@RequestBody UpdateSurveyRequest req, @PathVariable Long surveyId) {
        return service.updateSurvey(req, surveyId);
    }

    @Operation(summary = "설문 단일 삭제", description = "설문 id를 기반으로 단일 삭제")
    @DeleteMapping("{surveyId}")
    public void deleteSurvey(@PathVariable Long surveyId) {
        service.deleteSurvey(surveyId);
    }
}
