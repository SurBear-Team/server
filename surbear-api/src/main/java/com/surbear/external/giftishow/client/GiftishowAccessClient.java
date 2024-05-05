package com.surbear.external.giftishow.client;


import com.surbear.external.giftishow.dto.GoodsDetailDto;
import com.surbear.external.giftishow.dto.GoodsDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("https://bizapi.giftishow.com")
public interface GiftishowAccessClient {

//    @PostExchange("/bizApi/goods")
//    GoodsDto getGoodsList(
//            @RequestParam("api_code") String apiCode,
//            @RequestParam("custom_auth_code") String customAuthCode,
//            @RequestParam("custom_auth_token") String customAuthToken,
//            @RequestParam("dev_yn") String devYn,
//            @RequestParam("start") String start,
//            @RequestParam("size") String size
//    );

    @PostExchange("/bizApi/goods/{goods_code}")
    GoodsDetailDto getGood(
            @RequestParam("api_code") String apiCode,
            @RequestParam("custom_auth_code") String customAuthCode,
            @RequestParam("custom_auth_token") String customAuthToken,
            @RequestParam("dev_yn") String devYn,
            @PathVariable("goods_code") String goodsCode
    );
}
