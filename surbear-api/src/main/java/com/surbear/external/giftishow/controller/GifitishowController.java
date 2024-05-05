package com.surbear.external.giftishow.controller;


import com.surbear.external.giftishow.dto.GoodsDetailDto;
import com.surbear.external.giftishow.dto.GoodsDto;
import com.surbear.external.giftishow.service.GiftishowService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "기프티쇼", description = "기프티쇼 관련 외부 API")
@RequiredArgsConstructor
@RequestMapping("/external")
public class GifitishowController {

    private final GiftishowService service;

//    @PostMapping
//    public GoodsDto getGoodsList() throws Exception {
//        return service.getGoodsList();
//    }

    @PostMapping("/sample")
    public GoodsDetailDto getGood() throws Exception {
        return service.getGood();
    }

}
