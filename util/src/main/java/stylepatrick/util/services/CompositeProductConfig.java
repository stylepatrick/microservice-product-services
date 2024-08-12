package stylepatrick.util.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
// Only required without Eureka-Server
// @ConfigurationProperties(prefix = "app")
public class CompositeProductConfig {

    private String productServiceUrl = "product";
    private String recommendationServiceUrl = "recommendation";
    private String reviewServiceUrl = "review";
    private String compositeProductUrl = "composite-product";
}
