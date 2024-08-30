package stylepatrick.product.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stylepatrick.api.core.event.Event;
import stylepatrick.api.core.product.Product;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class MessageProcessorConfig {

    private final ProductServiceImpl productServiceImpl;

    @Bean
    public Consumer<Event<Integer, Product>> messageProcessor() {
        return event -> {

            switch (event.getEventType()) {

                case CREATE:
                    Product review = event.getData();
                    productServiceImpl.createProduct(review).block();
                    break;

                case DELETE:
                    Integer productId = event.getKey();
                    productServiceImpl.deleteProduct(productId).block();
                    break;

                default:
                    String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                    throw new IllegalArgumentException(errorMessage);
            }
        };
    }
}
