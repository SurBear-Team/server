package com.surbear.external.giftishow.dto;

import java.util.List;

public record ResultList(
        String listNum,
        List<GoodsList> goodsList
) {
}