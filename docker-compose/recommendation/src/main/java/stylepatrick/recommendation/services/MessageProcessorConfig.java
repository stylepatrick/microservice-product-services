package stylepatrick.recommendation.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stylepatrick.api.core.event.Event;
import stylepatrick.api.core.recommendation.Recommendation;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class MessageProcessorConfig {

    private final RecommendationServiceImpl recommendationServiceImpl;

    @Bean
    public Consumer<Event<Integer, Recommendation>> messageProcessor() {
        return event -> {

            switch (event.getEventType()) {

                case CREATE:
                    Recommendation review = event.getData();
                    recommendationServiceImpl.createRecommendation(review).block();
                    break;

                case DELETE:
                    Integer productId = event.getKey();
                    recommendationServiceImpl.deleteRecommendations(productId).block();
                    break;

                default:
                    String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                    throw new IllegalArgumentException(errorMessage);
            }
        };
    }
}
