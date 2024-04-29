package com.surbear.report.service;


import com.surbear.report.entity.SurveyReportEntity;
import com.surbear.report.mapper.SurveyReportMapper;
import com.surbear.report.model.SurveyReport;
import com.surbear.report.repository.SurveyReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReportService {

    private final SurveyReportRepository surveyReportRepository;
    private final SurveyReportMapper surveyReportMapper;


    @Transactional
    public Long report(SurveyReport surveyReport) {
        return create(surveyReport);
    }


    private Long create(SurveyReport surveyReport) {
        SurveyReportEntity newEntity = surveyReportMapper.toEntity(surveyReport);

        SurveyReportEntity savedEntity = surveyReportRepository.save(newEntity);
        return savedEntity.getId();
    }
}
