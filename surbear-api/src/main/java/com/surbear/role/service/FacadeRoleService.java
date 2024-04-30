package com.surbear.role.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.inferface.ParticipatedSurveyHistory;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.dto.survey.history.IdAndCreatedAtForSurveyHistory;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.dto.survey.history.ParticipatedSurveyForAdmin;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.model.Survey;
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
public class FacadeRoleService implements ParticipatedSurveyHistory<ParticipatedSurveyForAdmin> {

    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;


    public List<ParticipatedSurveyForAdmin> triggerGetParticipatedSurveyList(String nickname){
        Long memberId = getMemberByNickname(nickname).id();
        List<IdAndCreatedAtForSurveyHistory> historyRecords = getSurveyIdsByMemberId(memberId);
        List<Long> ids = extractIdsFromHistoryRecords(historyRecords);
        List<SurveyEntity> surveys = fetchSurveysByIds(ids);
        Map<Long, Instant> createdAtMap = createCreatedAtMap(historyRecords);
        return convertToParticipatedSurveys(surveys, createdAtMap);
    }

    @Override
    public List<IdAndCreatedAtForSurveyHistory> getSurveyIdsByMemberId(Long memberId) {
        return surveyAnswerRepository.findAllByMemberId(memberId);
    }

    @Override
    public List<Long> extractIdsFromHistoryRecords(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .map(IdAndCreatedAtForSurveyHistory::surveyId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Instant> createCreatedAtMap(List<IdAndCreatedAtForSurveyHistory> historyRecords) {
        return historyRecords.stream()
                .collect(Collectors.toMap(IdAndCreatedAtForSurveyHistory::surveyId, IdAndCreatedAtForSurveyHistory::createdAt));
    }

    @Override
    public List<SurveyEntity> fetchSurveysByIds(List<Long> ids) {
        return surveyRepository.findParticipatedSurveysByIds(ids);
    }

    @Override
    public List<ParticipatedSurveyForAdmin> convertToParticipatedSurveys(List<SurveyEntity> surveys, Map<Long, Instant> createdAtMap) {
        return surveys.stream()
                .map(survey -> new ParticipatedSurveyForAdmin(
                        survey.getId(),
                        survey.getTitle(),
                        getNicknameByMemberId(survey.getSurveyAuthorId()),
                        survey.isDeleted(),
                        createdAtMap.getOrDefault(survey.getId(), null)))
                .collect(Collectors.toList());
    }

    private String getNicknameByMemberId(Long memberId){
        return memberRepository.findById(memberId).get().getNickname();
    }

    public List<Survey> getMemberSurveyRecord(String nickname) {
        Member newMember = getMemberByNickname(nickname);
        validGetMemberByNickname(newMember);

        return getSurveyByMemberId(newMember.id());
    }

    public Member getMemberInfoByNickname(String nickname) {
        return checkNicknameExists(nickname);
    }

    private List<Survey> getSurveyByMemberId(Long memberId) {
        return surveyRepository.findAllBySurveyAuthorId(memberId);
    }

    private Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    private void validGetMemberByNickname(Member newMember) {
        if (newMember == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
    }

    private Member checkNicknameExists(String nickname) {
        Member member = memberRepository.findByNicknameAndDeletedIsFalse(nickname);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return member;
    }

}
