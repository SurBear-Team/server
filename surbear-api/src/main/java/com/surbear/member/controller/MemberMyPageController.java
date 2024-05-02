package com.surbear.member.controller;


import com.surbear.configuration.authorization.Authorization;
import com.surbear.member.service.MemberProfileService;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "회원 프로필", description = "프로필")
@RequiredArgsConstructor
@RequestMapping("/member/profile")
public class MemberMyPageController {


    private final MemberProfileService memberProfileService;

    @Operation(summary = "프로필 내 설문조사 내역", description = "사용자 id기반 설문 리스트 조회(인가)")
    @GetMapping("/list")
    public List<ParticipatedSurvey> getSurveyListByMemberIdAndDeletedIsFalse(
            @Authorization
            @Parameter(hidden = true)
            Long memberId
    ) {
        return memberProfileService.getSurveyListByMemberId(memberId);
    }


    @Operation(summary = "등록한 설문조사 갯수", description = "토큰기반 등록한 설문조사 갯수 불러오기")
    @GetMapping("/counting/survey")
    public Integer getSurveyCount(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return memberProfileService.getSurveyCount(memberId);
    }

    @Operation(summary = "참여한 설문조사 갯수", description = "토큰기반 참여한 설문조사 갯수 불러오기")
    @GetMapping("/counting/participation")
    public Integer getParticipationSurveyCount(
            @Authorization
            @Parameter(hidden = true)
            Long memberId) {
        return memberProfileService.getParticipationSurveyCount(memberId);
    }


}
