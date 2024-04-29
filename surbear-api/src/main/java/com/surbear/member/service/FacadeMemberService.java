package com.surbear.member.service;


import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacadeMemberService {

    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyRepository surveyRepository;


    @Transactional
    public List<ParticipatedSurvey> getParticipatedSurveyList(Long memberId) {
        List<IdAndCreatedAtForSurveyHistory> historyRecords = getSurveyIdsByMemberId(memberId);
        List<Long> ids = extractIdsFromHistoryRecords(historyRecords);
        List<SurveyEntity> surveys = fetchSurveysByIds(ids);
        Map<Long, Instant> createdAtMap = createCreatedAtMap(historyRecords);
        return convertToParticipatedSurveys(surveys, createdAtMap);
    }

    private List<IdAndCreatedAtForSurveyHistory> getSurveyIdsByMemberId(Long memberId) {
        return surveyAnswerRepository.findAllByMemberId(memberId);
    }

    private List<Long> extractIdsFromHistoryRecords(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .map(IdAndCreatedAtForSurveyHistory::surveyId)
                .collect(Collectors.toList());
    }


    private Map<Long, Instant> createCreatedAtMap(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .collect(Collectors.toMap(IdAndCreatedAtForSurveyHistory::surveyId, IdAndCreatedAtForSurveyHistory::createdAt));
    }

    private List<SurveyEntity> fetchSurveysByIds(List<Long> ids) {
        return surveyRepository.findParticipatedSurveysByIds(ids);
    }

    private List<ParticipatedSurvey> convertToParticipatedSurveys(List<SurveyEntity> surveys, Map<Long, Instant> createdAtMap) {
        return surveys.stream()
                .map(survey -> new ParticipatedSurvey(
                        survey.getId(),
                        survey.getTitle(),
                        survey.isOpenType(),
                        survey.isDeleted(),
                        createdAtMap.getOrDefault(survey.getId(), null)))
                .collect(Collectors.toList());
    }
}
