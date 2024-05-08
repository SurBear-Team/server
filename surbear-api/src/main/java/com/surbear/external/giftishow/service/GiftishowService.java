package com.surbear.external.giftishow.service;


import com.surbear.external.giftishow.client.GiftishowAccessClient;
import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.dto.GoodsResponseList;
import com.surbear.goods.mapper.GoodsMapper;
import com.surbear.goods.model.Goods;
import com.surbear.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GiftishowService {

    private final GiftishowAccessClient client;
    private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;


    @Transactional
    public void schedulerSaveGoods() throws Exception {
        GoodsResponseList goodsResponseList = getGoodsList();

        saveGoods(goodsResponseList);
    }


    private void saveGoods(GoodsResponseList goodsResponseList) {
        List<Goods> goodsList = goodsResponseList.result().goodsList();

        for (Goods goods : goodsList) {
            goodsRepository.save(goodsMapper.toEntity(goods));
        }

    }

    private GoodsResponseList getGoodsList() throws Exception {
        return client.getGoodsList(
                GET_GOODS_LIST,
                AUTHORIZATION_KEY,
                TOKEN_KEY,
                "N",
                "1",
                "500"
        );
    }

    public GoodsResponse getGoods(String goodsCode) throws Exception {
        return client.getGood(
                goodsCode,
                GET_GOODS,
                AUTHORIZATION_KEY,
                TOKEN_KEY,
                "N"
        );
    }


    @Value("${giftishow.key.authorization}")
    private String AUTHORIZATION_KEY;

    @Value("${giftishow.key.token}")
    private String TOKEN_KEY;

    private final static String GET_GOODS_LIST = "0101";
    private final static String GET_GOODS = "0111";


}
