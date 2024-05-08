package com.surbear.external.giftishow.service;


import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.external.giftishow.client.GiftishowAccessClient;
import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.dto.GoodsResponseList;
import com.surbear.goods.entity.GoodsEntity;
import com.surbear.goods.mapper.GoodsMapper;
import com.surbear.goods.model.Goods;
import com.surbear.goods.repository.GoodsRepository;
import com.surbear.survey.constants.OngoingType;
import com.surbear.survey.constants.SurveyType;
import com.surbear.survey.question.model.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
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


    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void schedulerSaveGoods() throws Exception {
        deleteAllGoods();

        GoodsResponseList goodsResponseList = getGoodsList();

        saveGoods(goodsResponseList);
    }


    private void saveGoods(GoodsResponseList goodsResponseList) {
        List<Goods> goodsList = goodsResponseList.result().goodsList();

        for (Goods goods : goodsList) {
            goodsRepository.save(goodsMapper.toEntity(goods));
        }

    }

    public Page<GoodsEntity> getGoodsToDb(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);

        return goodsRepository.findAll(pageable);
    }

    public List<GoodsEntity> searchGoodsByName(String goodsName) {
        List<GoodsEntity> goodsList = goodsRepository.findByGoodsNameContaining(goodsName);
        if (goodsList.isEmpty()){
            throw new ProcessErrorCodeException(BasicErrorCode.INVALID_PARAMETER);
        }
        return goodsList;
    }

    private void deleteAllGoods() {
        goodsRepository.deleteAll();
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
