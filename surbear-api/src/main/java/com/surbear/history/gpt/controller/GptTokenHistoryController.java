package com.surbear.history.gpt.controller;


import com.surbear.history.gpt.model.GptHistory;
import com.surbear.history.gpt.service.GptTokenHistoryService;
import com.surbear.member.model.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "지피티 사용량", description = "지피티 사용량 확인 API")
@RequiredArgsConstructor
@RequestMapping("/external/gpt")
public class GptTokenHistoryController {

    private final GptTokenHistoryService gptTokenHistoryService;

    @Operation(summary = "지피티 사용량 등록", description = "")
    @PostMapping("")
    public Long create(@RequestBody GptHistory gptHistory) {
        return gptTokenHistoryService.create(gptHistory);
    }

    @Operation(summary = "지피티 사용량 확인", description = "")
    @GetMapping("")
    public GptHistory get() {
        return gptTokenHistoryService.getLast();
    }
}
