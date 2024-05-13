package com.surbear.survey.answer.service;

import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.service.PointHistoryService;
import com.surbear.member.service.MemberService;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.dto.AnswerDto;
import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.model.SurveyQuestion;
import com.surbear.survey.question.service.QuestionPrecedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyAnswerService {

    private final AnswerPrecedingService precedingService;
    private final PointHistoryService pointHistoryService;
    private final QuestionPrecedingService questionPrecedingService;
    private final MemberService memberService;

    @Transactional
    public Long createSurveyAnswer(SurveyAnswer dto) {
        return precedingService.createSurveyAnswer(dto);
    }

    @Transactional
    public void saveListMemberAnswer(Long surveyAnswerId, List<AnswerDto> list) {
        list.forEach(dto -> {
            saveMemberAnswer(surveyAnswerId, dto);
        });
        Long questionId = list.get(0).questionId();

        Long surveyId = getSurveyIdByQuestionId(questionId);
        Survey survey = getSurveyById(surveyId);
        PointHistory pointHistory = pointHistoryService.createPointHistoryBySurvey(surveyAnswerId, survey.point());
        pointHistoryService.create(pointHistory);

        SurveyAnswer surveyAnswer = precedingService.getSurveyAnswerById(surveyAnswerId);
        memberService.changePoint(surveyAnswer.memberId(),survey.point());
    }

    private void saveMemberAnswer(Long surveyAnswerId, AnswerDto dto) {
        precedingService.saveMemberAnswer(surveyAnswerId, dto.questionId(), dto.answers());
    }

    private Long getSurveyIdByQuestionId(Long questionId) {
        SurveyQuestion surveyQuestion = questionPrecedingService.getSurveyQuestion(questionId);
        return surveyQuestion.surveyId();
    }

    private Survey getSurveyById(Long surveyId) {
        return questionPrecedingService.getSurvey(surveyId);
    }


    public Long getSurveyAnswer(SurveyAnswer surveyAnswer) {
        return (precedingService.getSurveyAnswer(surveyAnswer) == null)
                ? precedingService.createSurveyAnswer(surveyAnswer)
                : precedingService.getSurveyAnswer(surveyAnswer);
    }

}
