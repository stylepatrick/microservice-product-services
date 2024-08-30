package stylepatrick.review.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stylepatrick.api.core.event.Event;
import stylepatrick.api.core.review.Review;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class MessageProcessorConfig {

    private final ReviewServiceImpl reviewServiceImpl;

    @Bean
    public Consumer<Event<Integer, Review>> messageProcessor() {
        return event -> {

            switch (event.getEventType()) {

                case CREATE:
                    Review review = event.getData();
                    reviewServiceImpl.createReview(review).block();
                    break;

                case DELETE:
                    Integer productId = event.getKey();
                    reviewServiceImpl.deleteReviews(productId).block();
                    break;

                default:
                    String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                    throw new IllegalArgumentException(errorMessage);
            }
        };
    }
}
