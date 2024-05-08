package com.surbear.history.point.controller;

import com.surbear.common.authorization.Authorization;
import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.service.PointHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "포인트 조회", description = "포인트 조회 관련 API")
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointHistoryController {

    private final PointHistoryService pointHistoryService;


    @Operation(summary = "포인트 적립 내역 조회", description = "인가를 통해서 포인트 내역을 조회한다.")
    @GetMapping("/history")
    public List<PointHistory> getPointHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return pointHistoryService.getPointHistory(memberId);
    }
}
