package stylepatrick.compositeproduct.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class CompositeProductConfig {

    private String productServiceUrl;
    private String recommendationServiceUrl;
    private String reviewServiceUrl;
}
