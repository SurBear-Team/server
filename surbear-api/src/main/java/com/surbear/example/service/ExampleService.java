package com.surbear.example.service;

import com.surbear.mock.example.model.Example;
import com.surbear.mock.example.service.ExamplePrecedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExampleService {
    private final ExamplePrecedingService service;

    @Transactional
    public Long saveExample(Example example){
        return service.create(example);
    }
}
