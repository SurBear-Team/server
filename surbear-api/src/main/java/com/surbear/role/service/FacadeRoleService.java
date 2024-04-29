package com.surbear.role.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacadeRoleService {

    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;


    public List<Survey> getMemberSurveyRecord(String nickname) {
        Member newMember = getMemberByNickname(nickname);
        validGetMemberByNickname(newMember);

        List<Survey> surveyList = getSurveyByMemberId(newMember.id());
        return surveyList;
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
