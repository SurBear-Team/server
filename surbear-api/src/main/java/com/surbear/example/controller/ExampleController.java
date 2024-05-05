package com.surbear.example.controller;


import com.surbear.common.authorization.Authorization;
import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.mock.example.model.Example;
import com.surbear.example.service.ExampleService;
import com.surbear.mock.example.model.SurveyTestResponse;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "example", description = "테스트용 API")
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    public final ExampleService service;

    @Operation(summary = "예제 mock api", description = "예제 생성을 위한 mock api.")
    @PostMapping
    public Long create(@RequestBody Example example) {
        return service.saveExample(example);
    }

    @Operation(summary = "예제 오류발생 api", description = "전역 에러 처리를 위해 발생시키는 400 에러 mock api")
    @GetMapping("/error/400")
    public ResponseEntity<Void> badRequest() {
        throw new ProcessErrorCodeException(BasicErrorCode.INVALID_PARAMETER);
    }

    @Operation(summary = "예제 오류발생 api", description = "전역 에러 처리를 위해 발생시키는 500 에러 mock api")
    @GetMapping("/error/500")
    public ResponseEntity<Void> internalServerError() {
        throw new ProcessErrorCodeException(BasicErrorCode.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "토큰 인가", description = "토큰 인가 테스트")
    @GetMapping("/authorization")
    public Long testAuthorization(
            @Authorization
            @Parameter(hidden = true)
            Long memberId
    ) {
        return memberId;
    }

    @Operation(summary = "설문리스트 불러오는 mock api", description = "설문전체 조회 api 테스트 용")
    @GetMapping("/survey/list")
    public List<SurveyTestResponse> getSurveyList() {
        List<SurveyTestResponse> returnList = new ArrayList<>();
        returnList.add(SurveyTestResponse.builder()
                .id(1L)
                .surveyAuthorId("테스트 멤버")
                .point(20)
                .title("테스트 입니다")
                .description("123213123")
                .maximumNumberOfPeople(24)
                .deadLine(Instant.ofEpochSecond(2024 - 04 - 12))
                .openType(false)
                .ongoingType(OngoingType.CLOSE)
                .surveyType(SurveyType.EDUCATION)
                .deleted(false)
                .build());
        returnList.add(SurveyTestResponse.builder()
                .id(2L)
                .surveyAuthorId("아주세나")
                .point(30)
                .title("철권을 좋아하는 의문의 29살 노인")
                .description("아")
                .maximumNumberOfPeople(23)
                .deadLine(Instant.ofEpochSecond(2024 - 04 - 12))
                .openType(false)
                .ongoingType(OngoingType.PROGRESS)
                .surveyType(SurveyType.ETC)
                .deleted(false)
                .build());
        returnList.add(SurveyTestResponse.builder()
                .id(3L)
                .surveyAuthorId("조찬혁")
                .point(10)
                .title("잘생긴 동네형")
                .description("아아")
                .maximumNumberOfPeople(1)
                .deadLine(Instant.ofEpochSecond(2024 - 04 - 12))
                .openType(true)
                .ongoingType(OngoingType.MODIFICATION)
                .surveyType(SurveyType.EDUCATION)
                .deleted(false)
                .build());

        return returnList;
    }


}
