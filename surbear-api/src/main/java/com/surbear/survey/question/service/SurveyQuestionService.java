package com.surbear.survey.question.service;

import com.surbear.mock.example.entity.example.ExampleEntity;
import com.surbear.mock.example.model.Example;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.entity.SurveyQuestionOptionEntity;
import com.surbear.survey.question.mapper.SurveyMapper;
import com.surbear.survey.question.mapper.SurveyQuestionMapper;
import com.surbear.survey.question.mapper.SurveyQuestionOptionMapper;
import com.surbear.survey.question.model.Survey;
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
    // 작성자 id, 설문 제목, 설문 설명, 진행중 여부, 설문 유형,설문 비공개 여부,지급 포인트, 최대 인원

    @Transactional
    public void createQuestion() {

    }
    // 질문 내용, 필수 여부, 질문 형식, 페이지, 설문지 테이블 id, 최대 글자수

    @Transactional
    public void createQuestionOption(List<String> answers, Long questionId) {
        for (String answer : answers) {
            SurveyQuestionOption surveyQuestionOption = SurveyQuestionOption.builder()
                    .questionId(questionId)
                    .answer(answer)
                    .build();

            SurveyQuestionOptionEntity entity = surveyQuestionOptionMapper.toEntity(surveyQuestionOption);
            surveyQuestionOptionRepository.save(entity);
        }

    }

}
