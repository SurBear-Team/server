package com.surbear.survey.question.service;

import com.surbear.survey.dto.AnswersRequest;
import com.surbear.survey.dto.CreateSurveyRequest;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.entity.SurveyQuestionEntity;
import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import com.surbear.survey.question.mapper.SurveyMapper;
import com.surbear.survey.question.mapper.SurveyQuestionMapper;
import com.surbear.survey.question.mapper.SurveyQuestionOptionMapper;
import com.surbear.survey.question.model.Survey;
import com.surbear.survey.question.model.SurveyQuestion;
import com.surbear.survey.question.model.SurveyQuestionOption;
import com.surbear.survey.question.repository.SurveyQuestionOptionRepository;
import com.surbear.survey.question.repository.SurveyQuestionRepository;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyQuestionService {

    private final SurveyPrecedingService precedingService;


    @Transactional
    public Long createSurvey(CreateSurveyRequest req) {
        //TODO 지급 포인트 로직
        //TODO 순서 로직
        return precedingService.createSurvey(req);
    }

    @Transactional
    public Long createQuestion(SurveyQuestion surveyQuestion, List<String> answers) {
        Long questionId = generateQuestion(surveyQuestion);
        generateQuestionOption(answers, questionId);
        return questionId;
    }

    private Long generateQuestion(SurveyQuestion surveyQuestion) {
        return precedingService.createSurveyQuestion(surveyQuestion);
    }

    private void generateQuestionOption(List<String> list, Long questionId) {
        for (String answer : list) {
            precedingService.createSurveyQuestionOption(answer, questionId);
        }
    }
}
