package com.surbear.survey.answer.service;

import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.dto.AnswerDto;
import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.question.entity.SurveyEntity;
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

    @Transactional
    public List<ParticipatedSurvey> getParticipatedSurveyList(Long memberId) {
        List<IdAndCreatedAtForSurveyHistory> historyRecords = precedingService.getSurveyIdsByMemberId(memberId);
        List<Long> ids = precedingService.extractIdsFromHistoryRecords(historyRecords);
        List<SurveyEntity> surveys = precedingService.fetchSurveysByIds(ids);
        Map<Long, Instant> createdAtMap = precedingService.createCreatedAtMap(historyRecords);
        return precedingService.convertToParticipatedSurveys(surveys, createdAtMap);
    }

    @Transactional
    public Long createSurveyAnswer(SurveyAnswer dto) {
        return precedingService.createSurveyAnswer(dto);
    }

    @Transactional
    public void saveListMemberAnswer(Long surveyAnswerId, List<AnswerDto> list) {
        list.forEach(dto -> {
            saveMemberAnswer(surveyAnswerId, dto);
        });
    }

    private void saveMemberAnswer(Long surveyAnswerId, AnswerDto dto) {
        precedingService.saveMemberAnswer(surveyAnswerId, dto.questionId(), dto.answers());
    }


    public Long getSurveyAnswer(SurveyAnswer surveyAnswer) {
        return (precedingService.getSurveyAnswer(surveyAnswer) == null)
                ? precedingService.createSurveyAnswer(surveyAnswer)
                : precedingService.getSurveyAnswer(surveyAnswer);
    }

}
