package com.surbear.external.giftishow.dto;

import com.surbear.goods.model.Goods;

import java.util.List;

public record ResultList(
        String listNum,
        List<Goods> goodsList
) {
}