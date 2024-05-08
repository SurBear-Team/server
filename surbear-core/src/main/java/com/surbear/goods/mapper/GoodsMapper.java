package com.surbear.goods.mapper;

import com.surbear.goods.entity.GoodsEntity;
import com.surbear.goods.model.Goods;
import org.mapstruct.Mapper;

@Mapper
public interface GoodsMapper {
    GoodsEntity toEntity(Goods model);

    Goods toModel(GoodsEntity entity);

}
