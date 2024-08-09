package stylepatrick.compositeproduct.config;

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
}
