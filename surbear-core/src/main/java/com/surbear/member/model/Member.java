package com.surbear.member.model;

import com.surbear.member.constant.Age;
import com.surbear.member.constant.Gender;
import lombok.Builder;

@Builder
public record Member(
        Long id,
        Age age,
        Gender gender,
        String userId,
        String password,
        String email,
        Integer point,
        String nickname,
        boolean deleted
) {
}
