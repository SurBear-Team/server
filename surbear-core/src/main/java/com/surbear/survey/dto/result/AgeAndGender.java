package com.surbear.survey.dto.result;

import com.surbear.member.constant.Age;
import com.surbear.member.constant.Gender;
import lombok.Builder;

@Builder
public record AgeAndGender(
        Age age,
        Gender gender
) {
}
