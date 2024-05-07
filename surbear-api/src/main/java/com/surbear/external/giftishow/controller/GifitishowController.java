package com.surbear.external.giftishow.controller;


import com.surbear.external.giftishow.dto.ApiResponse;
import com.surbear.external.giftishow.dto.GoodsList;
import com.surbear.external.giftishow.dto.Result;
import com.surbear.external.giftishow.service.GiftishowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "기프티쇼", description = "기프티쇼 관련 외부 API")
@RequiredArgsConstructor
@RequestMapping("/external")
public class GifitishowController {

    private final GiftishowService service;

    @Operation(summary = "기프티쇼 상품 리스트 가져오기 api", description = "")
    @PostMapping("/list")
    public ApiResponse getGoodsList() throws Exception {
        return service.getGoodsList();
    }

//    @PostMapping("/sample")
//    public GoodsDetailDto getGood() throws Exception {
//        return service.getGood();
//    }

}
