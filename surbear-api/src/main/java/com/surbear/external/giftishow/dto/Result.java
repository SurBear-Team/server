package com.surbear.external.giftishow.dto;

import java.util.List;

public record Result(
        String listNum,
        List<GoodsList> goodsList
) {
}