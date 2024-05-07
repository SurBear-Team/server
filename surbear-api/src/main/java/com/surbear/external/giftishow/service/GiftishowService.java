package com.surbear.external.giftishow.service;


import com.surbear.common.encryption.Aes256Util;
import com.surbear.external.giftishow.client.GiftishowAccessClient;
import com.surbear.external.giftishow.dto.ApiResponse;
import com.surbear.external.giftishow.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GiftishowService {

    private final GiftishowAccessClient client;
    private final Aes256Util aes256Util;


    public ApiResponse getGoodsList() throws Exception {
        return client.getGoodsList(
                GET_GOODS_LIST,
                AUTHORIZATION_KEY,
                generateAuthToken(),
                "N",
                "1",
                "20"
        );
    }

    private String generateAuthToken() throws Exception {
        return aes256Util.encrypt(TOKEN_KEY, AUTHORIZATION_KEY);
    }

    @Value("${giftishow.key.authorization}")
    private String AUTHORIZATION_KEY;

    @Value("${giftishow.key.token}")
    private String TOKEN_KEY;

    private final static String GET_GOODS_LIST = "0101";

}
