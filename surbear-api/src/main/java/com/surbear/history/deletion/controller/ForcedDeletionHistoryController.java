package com.surbear.history.deletion.controller;


import com.surbear.configuration.authorization.Authorization;
import com.surbear.history.deletion.controller.dto.DeletionRequest;
import com.surbear.history.deletion.service.ForcedDeletionHistoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "강제삭제 기록", description = "강제삭제 관련 API")
@RequiredArgsConstructor
@RequestMapping("/deletion")
public class ForcedDeletionHistoryController {

    private final ForcedDeletionHistoryService service;

    @PostMapping
    public boolean forcedDeletion(
            @RequestBody DeletionRequest req,
            @Authorization
            @Parameter(hidden = true)
            Long memberId
    ){
        return service.forcedDeletion(memberId,req.surveyId());
    }
}
