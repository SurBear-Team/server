package com.surbear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SurbearApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurbearApiApplication.class, args);
    }

}
