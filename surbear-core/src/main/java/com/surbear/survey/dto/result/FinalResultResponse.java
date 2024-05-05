package com.surbear.survey.dto.result;

import com.surbear.member.constant.Age;
import com.surbear.member.constant.Gender;
import lombok.Builder;

import java.util.List;

@Builder
public record FinalResultResponse(
        Age age,
        Gender gender,
        List<ResultResponse> response
) {
}
