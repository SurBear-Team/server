package com.surbear.member.service;

import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberProfileService {

    private final SurveyRepository surveyRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;


    public Integer getSurveyCount(Long memberId){
        return surveyRepository.countAllBySurveyAuthorId(memberId);
    }

    public Integer getParticipationSurveyCount(Long memberId){
        return surveyAnswerRepository.countAllByMemberId(memberId);
    }
}
