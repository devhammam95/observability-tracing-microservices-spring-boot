package observability.com.example.order;

import feign.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	} 

	@Bean
	public RabbitTemplate rabbitTemplate(@org.springframework.lang.NonNull ConnectionFactory connectionFactory) {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			// This is the manual trigger for traceparent injection
			rabbitTemplate.setObservationEnabled(true); 
			return rabbitTemplate; 
		}
 
}
