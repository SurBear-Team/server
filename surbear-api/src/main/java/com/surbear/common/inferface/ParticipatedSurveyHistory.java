package com.surbear.common.inferface;

import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.question.entity.SurveyEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ParticipatedSurveyHistory<T> {

    List<IdAndCreatedAtForSurveyHistory> getSurveyIdsByMemberId(Long memberId);

    List<Long> extractIdsFromHistoryRecords(List<IdAndCreatedAtForSurveyHistory> historyRecords);


    Map<Long, Instant> createCreatedAtMap(List<IdAndCreatedAtForSurveyHistory> historyRecords);

    List<SurveyEntity> fetchSurveysByIds(List<Long> ids);

    List<T> convertToParticipatedSurveys(List<SurveyEntity> surveys, Map<Long, Instant> createdAtMap);
}
