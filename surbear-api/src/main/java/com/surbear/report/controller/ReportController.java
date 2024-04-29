package com.surbear.report.controller;


import com.surbear.report.model.SurveyReport;
import com.surbear.report.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "신고", description = "신고 관련 API")
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;


    @PostMapping("")
    public Long report(@RequestBody SurveyReport surveyReport) {
        return reportService.report(surveyReport);
    }
}
