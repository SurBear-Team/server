package com.surbear.survey;

import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.dto.UpdateSurveyOngoingTypeRequest;
import com.surbear.survey.question.service.QuestionPrecedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class SurveySchedulingConfig {

    private final QuestionPrecedingService questionPrecedingService;
    private final TaskScheduler taskScheduler;


    public void scheduleSurveyClose(Instant deadline, Long surveyId) {
        UpdateSurveyOngoingTypeRequest req = UpdateSurveyOngoingTypeRequest.builder()
                .id(surveyId)
                .type(OngoingType.CLOSE)
                .build();
        taskScheduler.schedule(() -> questionPrecedingService.updateSurveyOnGoingType(req), deadline);
    }
}
