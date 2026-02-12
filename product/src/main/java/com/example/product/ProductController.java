package com.example.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/product")
public class ProductController {


    @GetMapping
    public List<String> list()
    {

        log.info("Products api" + MDC.get("traceId"));
        return List.of("product1", "product2", "product3");
    }
}
