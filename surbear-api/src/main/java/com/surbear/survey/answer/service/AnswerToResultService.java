package com.surbear.survey.answer.service;

import com.surbear.member.repository.MemberRepository;
import com.surbear.survey.answer.model.SurveyAnswer;
import com.surbear.survey.answer.repository.MemberAnswerRepository;
import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.constants.QuestionType;
import com.surbear.survey.dto.AnswersRequest;
import com.surbear.survey.dto.result.AgeAndGender;
import com.surbear.survey.dto.result.FinalResultResponse;
import com.surbear.survey.dto.result.ResultResponse;
import com.surbear.survey.question.repository.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerToResultService {

    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final MemberAnswerRepository memberAnswerRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public List<FinalResultResponse> getFinalSurveyResult(Long surveyId, List<Long> questionIdList) {
        List<FinalResultResponse> finalResponses = new ArrayList<>();
        List<SurveyAnswer> surveyAnswerList = getSurveyAnswerList(surveyId);

        for (SurveyAnswer surveyAnswer : surveyAnswerList) {
            AgeAndGender ageAndGender = getMemberAgeAndGender(surveyAnswer.memberId());

            List<ResultResponse> resultResponses = new ArrayList<>();
            for (Long questionId : questionIdList) {
                QuestionType questionType = getQuestionTypeById(questionId);

                AnswersRequest answersRequest = getMemberAnswer(questionId, surveyAnswer.id());
                resultResponses.add(createResultResponse(questionId, questionType, answersRequest));
            }
            finalResponses.add(createFinalResultResponse(ageAndGender, resultResponses));
        }
        return finalResponses;
    }

    private ResultResponse createResultResponse(Long questionId, QuestionType questionType, AnswersRequest req) {
        return ResultResponse.builder()
                .questionId(questionId)
                .questionType(questionType)
                .request(req)
                .build();
    }

    private FinalResultResponse createFinalResultResponse(AgeAndGender ageAndGender, List<ResultResponse> resultResponses) {
        return FinalResultResponse.builder()
                .age(ageAndGender.age())
                .gender(ageAndGender.gender())
                .response(resultResponses)
                .build();
    }

    private List<SurveyAnswer> getSurveyAnswerList(Long surveyId) {
        return surveyAnswerRepository.findALlBySurveyIdAndDeletedIsFalse(surveyId);
    }

    private AgeAndGender getMemberAgeAndGender(Long memberId) {
        return memberRepository.findByIdAndDeletedIsFalse(memberId);
    }

    private QuestionType getQuestionTypeById(Long questionId) {
        return surveyQuestionRepository.findByIdAndDeletedIsFalse(questionId).getQuestionType();
    }

    private AnswersRequest getMemberAnswer(Long questionId, Long surveyAnswerId) {
        List<String> list = memberAnswerRepository.findAnswersBySurveyQuestionIdAndSurveyAnswerId(questionId, surveyAnswerId);
        return AnswersRequest.builder()
                .answers(list)
                .build();
    }
}
