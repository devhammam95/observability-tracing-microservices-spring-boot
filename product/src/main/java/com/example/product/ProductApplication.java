package com.example.product;

import org.slf4j.MDC;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
			ConnectionFactory connectionFactory) {
		
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setObservationEnabled(true); // Enables automatic MDC population

		factory.setAfterReceivePostProcessors(message -> {
			String traceParent = (String) message.getMessageProperties().getHeader("traceparent");
			
			if (traceParent != null && traceParent.contains("-")) {
				String[] parts = traceParent.split("-");
				if (parts.length >= 2) {
					String pureTraceId = parts[1]; // The actual 32-char traceId
					//message.getMessageProperties().setHeader("traceId", pureTraceId);
					MDC.put("traceId", pureTraceId);
				}
			} 
			return message;
		});

		return factory;
	}

}
