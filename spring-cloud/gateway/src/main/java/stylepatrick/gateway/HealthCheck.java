package stylepatrick.gateway;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stylepatrick.gateway.service.HealthCheckService;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class HealthCheck {

    private final HealthCheckService healthCheckService;

    @Bean
    ReactiveHealthContributor microServices() {

        Map<String, ReactiveHealthIndicator> registry = new LinkedHashMap<>();

        registry.put("product", healthCheckService::getProductHealth);
        registry.put("recommendation", healthCheckService::getRecommendationHealth);
        registry.put("review", healthCheckService::getReviewHealth);
        registry.put("composite-product", healthCheckService::getCompositeProductHealth);

        return CompositeReactiveHealthContributor.fromMap(registry);
    }
}
