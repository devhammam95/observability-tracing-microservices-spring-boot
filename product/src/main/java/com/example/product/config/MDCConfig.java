package com.example.product.config;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MDCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                // Generate a trace ID if not present
                String traceId = request.getHeader("traceparent");
                
                System.out.println("Trace ID: " + traceId);
                // Set trace ID in MDC
                MDC.put("traceId", traceId);
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                     Object handler, Exception ex) {
                // Clear the MDC after the request is complete
                MDC.clear();
            }
        });
    }
}