package com.surbear.external.giftishow.dto;

import java.util.List;

public record GoodsDto(
        String listNum,
        List<GoodsList> goodsList
) {
}