package com.surbear.history.deletion.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.history.deletion.entity.ForcedDeletionHistoryEntity;
import com.surbear.history.deletion.mapper.ForcedDeletionHistoryMapper;
import com.surbear.history.deletion.model.ForcedDeletionHistory;
import com.surbear.history.deletion.repository.ForcedDeletionHistoryRepository;
import com.surbear.member.model.Member;
import com.surbear.member.service.MemberService;
import com.surbear.role.service.RoleService;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.dto.UpdateSurveyOngoingTypeRequest;
import com.surbear.survey.dto.deletion.GetDeletionHistory;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.service.QuestionPrecedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ForcedDeletionHistoryService {
    private final ForcedDeletionHistoryMapper forcedDeletionHistoryMapper;
    private final ForcedDeletionHistoryRepository forcedDeletionHistoryRepository;
    private final RoleService roleService;
    private final QuestionPrecedingService questionPrecedingService;
    private final MemberService memberService;


    public List<GetDeletionHistory> getDeletionList(String nickname) {
        Member member = memberService.findByNicknameAndDeletedIsFalse(nickname);

        List<Survey> newSurveyList = questionPrecedingService.getSurveyByAuthorIdAndOngoingType(member.id());
        List<Long> ids = newSurveyList.stream()
                .map(Survey::id)
                .toList();

        List<ForcedDeletionHistory> forcedDeletionHistories = forcedDeletionHistoryRepository.findAllByDeletedSurveyIdIn(ids);

        List<GetDeletionHistory> dtoList = forcedDeletionHistories.stream()
                .map(history -> GetDeletionHistory.builder()
                                .createdAt(history.createdAt())
                                .nickname(getNickname(history.adminMemberId()))
                                .title(getTitle(history.deletedSurveyId()))
                                .build()
                )
                .toList();

        return dtoList;
    }


    private String getNickname(Long memberId) {
        return memberService.findByNicknameById(memberId);
    }

    private String getTitle(Long surveyId) {
        return questionPrecedingService.getSurvey(surveyId).title();
    }


    @Transactional
    public boolean forcedDeletion(Long memberId, Long surveyId) {
        roleService.checkPermissionExists(memberId);
        ForcedDeletionHistory newModel = ForcedDeletionHistory.builder()
                .id(null)
                .adminMemberId(memberId)
                .deletedSurveyId(surveyId)
                .deleted(false)
                .build();

        checkDuplicate(surveyId);
        create(newModel);
        setOngoingType(surveyId);
        return true;
    }

    private void setOngoingType(Long surveyId) {
        UpdateSurveyOngoingTypeRequest req = UpdateSurveyOngoingTypeRequest.builder()
                .id(surveyId)
                .type(OngoingType.FORCED_DELETION)
                .build();
        questionPrecedingService.updateSurveyOnGoingType(req);
    }

    private void checkDuplicate(Long surveyId) {
        Survey survey = questionPrecedingService.getSurvey(surveyId);
        if (survey.ongoingType() == OngoingType.FORCED_DELETION) {
            throw new ProcessErrorCodeException(BasicErrorCode.CONFLICT);
        }
    }


    private Long create(ForcedDeletionHistory forcedDeletionHistory) {
        ForcedDeletionHistoryEntity newEntity = forcedDeletionHistoryMapper.toEntity(forcedDeletionHistory);
        ForcedDeletionHistoryEntity savedEntity = forcedDeletionHistoryRepository.save(newEntity);

        return savedEntity.getId();
    }


}
