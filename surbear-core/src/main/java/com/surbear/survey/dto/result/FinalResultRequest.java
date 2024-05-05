package com.surbear.survey.dto.result;

import java.util.List;

public record FinalResultRequest(
        Long id,
        List<Long> questIdList
) {
}
