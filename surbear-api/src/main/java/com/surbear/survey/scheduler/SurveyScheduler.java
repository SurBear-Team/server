package com.surbear.survey.scheduler;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SurveyScheduler {

    private final SurveyRepository surveyRepository;

    // 매 시간마다 실행되는 스케줄러 메서드
    @Scheduled(fixedRate = 3600000)  // 3600000 ms = 1 hour
    public void closeExpiredSurveys() {
        List<SurveyEntity> surveys = surveyRepository.findAllByOngoingTypeInAndDeletedFalse(Arrays.asList(OngoingType.PROGRESS, OngoingType.PAUSE));

        surveys.forEach(survey -> {
            if (survey.getDeadLine().isBefore(Instant.now())) {
                survey.setOngoingType(OngoingType.CLOSE);
                surveyRepository.save(survey);
            }
        });
    }
}

