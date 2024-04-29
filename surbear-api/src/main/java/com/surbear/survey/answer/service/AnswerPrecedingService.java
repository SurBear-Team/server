package com.surbear.survey.answer.service;

import com.surbear.survey.answer.entity.MemberAnswerEntity;
import com.surbear.survey.answer.entity.SurveyAnswerEntity;
import com.surbear.survey.answer.mapper.MemberAnswerMapper;
import com.surbear.survey.answer.mapper.SurveyAnswerMapper;
import com.surbear.survey.answer.model.MemberAnswer;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.answer.repository.MemberAnswerRepository;
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
public class AnswerPrecedingService {
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyAnswerMapper surveyAnswerMapper;
    private final MemberAnswerRepository memberAnswerRepository;
    private final MemberAnswerMapper memberAnswerMapper;
    private final SurveyRepository surveyRepository;

    @Transactional
    public Long createSurveyAnswer(SurveyAnswer surveyAnswer) {
        SurveyAnswerEntity newEntity = surveyAnswerMapper.toEntity(surveyAnswer);

        SurveyAnswerEntity savedEntity = surveyAnswerRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public Long createMemberAnswer(MemberAnswer memberAnswer) {
        MemberAnswerEntity newEntity = memberAnswerMapper.toEntity(memberAnswer);

        MemberAnswerEntity savedEntity = memberAnswerRepository.save(newEntity);

        return savedEntity.getId();
    }

    @Transactional
    public void saveMemberAnswer(Long surveyAnswerId, Long questionId, List<String> answers) {
        answers.forEach(answer -> {
            MemberAnswer newDto = MemberAnswer.builder()
                    .surveyQuestionId(questionId)
                    .surveyAnswerId(surveyAnswerId)
                    .answer(answer)
                    .build();

            createMemberAnswer(newDto);
        });
    }

    public Long getSurveyAnswer(SurveyAnswer surveyAnswer) {
        return surveyAnswerRepository.findFirstByMemberIdAndSurveyIdAndDeletedIsFalse(surveyAnswer.memberId(), surveyAnswer.surveyId());
    }

    List<IdAndCreatedAtForSurveyHistory> getSurveyIdsByMemberId(Long memberId) {
        return surveyAnswerRepository.findAllByMemberId(memberId);
    }

    List<Long> extractIdsFromHistoryRecords(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .map(IdAndCreatedAtForSurveyHistory::surveyId)
                .collect(Collectors.toList());
    }


    Map<Long, Instant> createCreatedAtMap(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .collect(Collectors.toMap(IdAndCreatedAtForSurveyHistory::surveyId, IdAndCreatedAtForSurveyHistory::createdAt));
    }

    List<SurveyEntity> fetchSurveysByIds(List<Long> ids) {
        return surveyRepository.findParticipatedSurveysByIds(ids);
    }

    List<ParticipatedSurvey> convertToParticipatedSurveys(List<SurveyEntity> surveys, Map<Long, Instant> createdAtMap) {
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

