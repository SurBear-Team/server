package com.surbear.example.controller;


import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.mock.example.model.Example;
import com.surbear.example.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "example", description = "테스트용 API")
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    public final ExampleService service;

    @Operation(summary = "예제 mock api", description = "예제 생성을 위한 mock api.")
    @PostMapping
    public Long create(@RequestBody Example example){
        return service.saveExample(example);
    }

    @Operation(summary = "예제 오류발생 api", description = "전역 에러 처리를 위해 발생시키는 400 에러 mock api")
    @GetMapping("/error/400")
    public ResponseEntity<Void> badRequest(){
        throw new ProcessErrorCodeException(BasicErrorCode.INVALID_PARAMETER);
    }

    @Operation(summary = "예제 오류발생 api", description = "전역 에러 처리를 위해 발생시키는 500 에러 mock api")
    @GetMapping("/error/500")
    public ResponseEntity<Void> internalServerError(){
        throw new ProcessErrorCodeException(BasicErrorCode.INTERNAL_SERVER_ERROR);
    }
}
