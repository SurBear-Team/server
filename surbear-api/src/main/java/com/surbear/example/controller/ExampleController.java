package com.surbear.example.controller;


import com.surbear.mock.example.model.Example;
import com.surbear.example.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "example", description = "테스트용 API")
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    public final ExampleService service;

    @Operation(summary = "예제용 mock api", description = "예제 생성을 위한 mock api.")
    @PostMapping
    public Long create(@RequestBody Example example){
        return service.saveExample(example);
    }
}
