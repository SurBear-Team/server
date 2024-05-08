package com.surbear.external.giftishow.controller;


import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.service.GiftishowService;
import com.surbear.goods.entity.GoodsEntity;
import com.surbear.goods.model.Goods;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "기프티쇼", description = "기프티쇼 관련 외부 API")
@RequiredArgsConstructor
@RequestMapping("/external")
public class GiftishowController {

    private final GiftishowService service;

    @Operation(summary = "상품 가져오기 pageable", description = "상품가져오기 페이저블")
    @GetMapping("/{page}/{number}")
    public Page<GoodsEntity> getGoodsList(@PathVariable int page, @PathVariable int number) throws Exception {
        return service.getGoodsToDb(page, number);
    }

    @Operation(summary = "상품 검색", description = "키워드만 일치하면 연관검색 가능")
    @GetMapping("{name}")
    public List<GoodsEntity> searchGoodsList(@PathVariable String name) throws Exception {
        return service.searchGoodsByName(name);
    }

    @GetMapping("")
    public GoodsResponse getGood(@RequestParam String goodsCode) throws Exception {
        return service.getGoods(goodsCode);
    }

}
