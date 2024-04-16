package com.surbear.survey.question.service;

import com.surbear.mock.example.entity.example.ExampleEntity;
import com.surbear.mock.example.model.Example;
import com.surbear.survey.question.dto.AnswersRequest;
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

    private final SurveyQuestionOptionRepository surveyQuestionOptionRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionOptionMapper surveyQuestionOptionMapper;
    private final SurveyQuestionMapper surveyQuestionMapper;
    private final SurveyMapper surveyMapper;


    @Transactional
    public Long createSurvey(Survey survey) {
        //TODO 지급 포인트 로직
        SurveyEntity surveyEntity = surveyMapper.toEntity(survey);
        return surveyRepository.save(surveyEntity).getId();
    }

    @Transactional
    public Long createQuestion(SurveyQuestion surveyQuestion,AnswersRequest req){
        Long questionId = generateQuestion(surveyQuestion);
        generateQuestionOption(req, questionId);
        return questionId;
    }

    private Long generateQuestion(SurveyQuestion surveyQuestion) {
        SurveyQuestionEntity surveyQuestionEntity = surveyQuestionMapper.toEntity(surveyQuestion);
        return surveyQuestionRepository.save(surveyQuestionEntity).getId();
    }

    private void generateQuestionOption(AnswersRequest req, Long questionId) {
        for (String answer : req.answers()) {
            SurveyQuestionOption surveyQuestionOption = SurveyQuestionOption.builder()
                    .questionId(questionId)
                    .answer(answer)
                    .build();

            SurveyQuestionOptionEntity entity = surveyQuestionOptionMapper.toEntity(surveyQuestionOption);
            surveyQuestionOptionRepository.save(entity);
        }

    }

}
