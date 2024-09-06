package stylepatrick.util.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class CompositeProductConfig {

    private String productServiceUrl = "product-service";
    private String recommendationServiceUrl = "recommendation-service";
    private String reviewServiceUrl = "review-service";
}
