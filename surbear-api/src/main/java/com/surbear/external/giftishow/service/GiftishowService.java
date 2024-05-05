package com.surbear.external.giftishow.service;


import com.surbear.common.encryption.Aes256Util;
import com.surbear.external.giftishow.client.GiftishowAccessClient;
import com.surbear.external.giftishow.dto.GoodsDetailDto;
import com.surbear.external.giftishow.dto.GoodsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GiftishowService {

    private final GiftishowAccessClient client;
    private final Aes256Util aes256Util;


//    public GoodsDto getGoodsList() throws Exception {
//        return client.getGoodsList(
//                "0111",
//                AUTHORIZATION_KEY,
//                generateAuthToken(),
//                "N",
//                "1",
//                "20");
//    }

//    public GoodsDetailDto getGood() throws Exception {
//        return client.getGood(
//                "0111",
//                AUTHORIZATION_KEY,
//                generateAuthToken(),
//                "N",
//                "G00000280811");
//    }
//
//    private String generateAuthToken() throws Exception {
//        return aes256Util.encrypt(TOKEN_KEY, AUTHORIZATION_KEY);
//    }

}
