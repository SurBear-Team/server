package com.surbear.goods.model;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record Goods(
        String goodsCode,
        String salePrice,
        String goodsImgS,
        String goodsName,
        String brandName,
        String goodsTypeDtlNm
) {
}
