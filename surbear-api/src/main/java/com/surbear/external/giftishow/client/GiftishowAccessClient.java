package com.surbear.external.giftishow.client;


import com.surbear.external.giftishow.dto.ApiResponse;
import com.surbear.external.giftishow.dto.GoodsList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("")
public interface GiftishowAccessClient {

    @PostExchange("https://bizapi.giftishow.com/bizApi/goods")
    ApiResponse getGoodsList(
            @RequestParam("api_code") String api_code,
            @RequestParam("custom_auth_code") String custom_auth_code,
            @RequestParam("custom_auth_token") String custom_auth_token,
            @RequestParam("dev_yn") String dev_yn,
            @RequestParam("start") String start,
            @RequestParam("size") String size
    );

    @PostExchange("/bizApi/goods/{goods_code}")
    GoodsList getGood(
            @RequestParam("api_code") String apiCode,
            @RequestParam("custom_auth_code") String customAuthCode,
            @RequestParam("custom_auth_token") String customAuthToken,
            @PathVariable("goods_code") String goodsCode,
            @RequestParam("dev_yn") String devYn
    );
}
