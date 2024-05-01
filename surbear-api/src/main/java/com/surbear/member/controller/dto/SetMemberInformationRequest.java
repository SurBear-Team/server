package com.surbear.member.controller.dto;

import com.surbear.member.constant.Age;

public record SetMemberInformationRequest(
        Age age,
        String nickname
) {
}
