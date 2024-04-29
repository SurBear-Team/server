package com.surbear.survey.answer.controller;

import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.answer.service.SurveyAnswerService;
import com.surbear.survey.dto.AnswerDto;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "설문 응답", description = "설문 응답 관련 API")
@RequiredArgsConstructor
@RequestMapping("/survey/answer")
public class SurveyAnswerController {

    private final SurveyAnswerService service;

    @Operation(summary = "답변지 시작", description = "사용자 id와 설문 id를 이용한 답변지 생성 만약 존재하는 답변지의 경우 생성하지 않고 기존 답변지 id리턴")
    @PostMapping("")
    public Long createSurveyAnswer(@RequestBody SurveyAnswer req) {
        return service.getSurveyAnswer(req);
    }

    @Operation(summary = "유저 답변 저장", description = "답변을 담은 answerDto 와 답변자의 아이디 입력")
    @PostMapping("/{surveyAnswerId}")
    public ResponseEntity<Void> createSurveyAnswer(@RequestBody List<AnswerDto> dto, @PathVariable Long surveyAnswerId) {
        service.saveListMemberAnswer(surveyAnswerId, dto);
        return ResponseEntity.ok().build();
    }
}
