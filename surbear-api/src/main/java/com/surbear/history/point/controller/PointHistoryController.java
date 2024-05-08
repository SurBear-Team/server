package com.surbear.history.point.controller;

import com.surbear.common.authorization.Authorization;
import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.service.PointHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @Operation(summary = "포인트 적립 내역 조회(회원용)", description = "인가를 통해서 포인트 내역을 조회한다.(회원용)")
    @GetMapping("/history")
    public List<PointHistory> getPointHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return pointHistoryService.getPointHistory(memberId);
    }

    @Operation(summary = "포인트 적립 내역 조회(관리자용)", description = "인가를 통해서 포인트 내역을 조회한다.(회원용)")
    @GetMapping("/history/admin")
    public List<PointHistory> getPointHistory(@RequestParam String nickname) {
        return pointHistoryService.getPointHistory(nickname);
    }

    @Operation(summary = "포인트 지급(관리자용)", description = "인가를 통해서 포인트를 지급한다.(회원용)")
    @PostMapping("")
    public void getPointHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId,
            @RequestParam String nickname, @RequestParam Integer paidPoint) {
        pointHistoryService.pointPayment(memberId, nickname, paidPoint);
    }

    @Operation(summary = "포인트 지급 취소(관리자용)", description = "인가를 통해서 포인트를 취소한다.(회원용)")
    @PostMapping("/canceling")
    public void cancelPointHistory(
            @Authorization
            @Parameter(hidden = true)
            Long memberId,
            @RequestParam Long pointHistoryId) {
        pointHistoryService.cancelPayment(memberId, pointHistoryId);
    }
}
