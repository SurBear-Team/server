package com.surbear.external.giftishow.controller;


import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.dto.GoodsResponseList;
import com.surbear.external.giftishow.dto.GoodsDetail;
import com.surbear.external.giftishow.service.GiftishowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "기프티쇼", description = "기프티쇼 관련 외부 API")
@RequiredArgsConstructor
@RequestMapping("/external")
public class GiftishowController {

    private final GiftishowService service;

    @Operation(summary = "기프티쇼 상품 리스트 가져오기 api", description = "")
    @GetMapping("/list")
    public GoodsResponseList getGoodsList() throws Exception {
        return service.getGoodsList();
    }

    @GetMapping("")
    public GoodsResponse getGood(@RequestParam String goodsCode) throws Exception {
        return service.getGoods(goodsCode);
    }

}
