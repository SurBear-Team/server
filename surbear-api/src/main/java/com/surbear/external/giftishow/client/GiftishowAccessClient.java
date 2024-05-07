package com.surbear.external.giftishow.client;


import com.surbear.external.giftishow.dto.ApiResponse;
import com.surbear.external.giftishow.dto.GoodsList;
import com.surbear.external.giftishow.dto.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@HttpExchange("https://bizapi.giftishow.com")
public interface GiftishowAccessClient {

    @PostExchange("/bizApi/goods")
    ApiResponse getGoodsList(
            @RequestParam("api_code") String api_code,
            @RequestParam("custom_auth_code") String custom_auth_code,
            @RequestParam("custom_auth_token") String custom_auth_token,
            @RequestParam("dev_yn") String dev_yn,
            @RequestParam("start") String start,
            @RequestParam("size") String size
    );

//    @PostExchange("/bizApi/goods/{goods_code}")
//    GoodsDetailDto getGood(
//            @RequestParam("api_code") String apiCode,
//            @RequestParam("custom_auth_code") String customAuthCode,
//            @RequestParam("custom_auth_token") String customAuthToken,
//            @RequestParam("dev_yn") String devYn,
//            @PathVariable("goods_code") String goodsCode
//    );
}
