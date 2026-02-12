package observability.com.example.order;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ProductClient productClient;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<String> getUser() 
    {
        System.out.println("otelTraceId=" + MDC.get("traceId"));

        log.info("Di Dina"); 

        return productClient.getAllProducts();
    }

    @GetMapping("/trace")
    public String getTraceId() {
        rabbitTemplate.convertAndSend("order-exchange", "order.created", "Order created" + MDC.get("traceId"));
        return MDC.get("traceId");
    }
}
