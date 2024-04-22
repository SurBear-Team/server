package com.surbear.survey.answer.service;

import com.surbear.survey.answer.entity.MemberAnswerEntity;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.dto.AnswerDto;
import com.surbear.survey.dto.QuestionAndOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyAnswerService {

    private final AnswerPrecedingService precedingService;

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

//    @Transactional
//    public void saveMemberAnswer(Long surveyAnswerMemberId, Long questionId, List<String> answers) {
//        answers.forEach(answer -> {
//            memberAnswerRepository.save(new MemberAnswerEntity(questionId, surveyAnswerMemberId, questionId, answer, false));
//        });
//    }

    public Long getSurveyAnswer(SurveyAnswer surveyAnswer) {
        return (precedingService.getSurveyAnswer(surveyAnswer) == null)
                ? precedingService.createSurveyAnswer(surveyAnswer)
                : precedingService.getSurveyAnswer(surveyAnswer);
    }

}
