package com.example.product;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductConsumer {

    @RabbitListener(queues = "product-queue")
    public void handleOrderMessage(String message, @Header(value = "traceparent", required = false) String traceparent) {
            String traceId = Optional.ofNullable(traceparent)
            .orElse("no-trace-id");
        log.info("Received Header: {}", traceId);
        log.info("Product of : {} received ", message);
        
    }
}