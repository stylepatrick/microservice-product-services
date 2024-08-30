package stylepatrick.gateway.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import stylepatrick.util.services.CompositeProductConfig;

@Service
public class HealthCheckService {

    private final WebClient webClient;
    private final CompositeProductConfig compositeProductConfig;

    public HealthCheckService(WebClient.Builder webClient,
                              CompositeProductConfig compositeProductConfig
    ) {
        this.webClient = webClient.build();
        this.compositeProductConfig = compositeProductConfig;
    }

    public Mono<Health> getProductHealth() {
        return getHealth(compositeProductConfig.getProductServiceUrl());
    }

    public Mono<Health> getRecommendationHealth() {
        return getHealth(compositeProductConfig.getRecommendationServiceUrl());
    }

    public Mono<Health> getReviewHealth() {
        return getHealth(compositeProductConfig.getReviewServiceUrl());
    }

    public Mono<Health> getCompositeProductHealth() {
        return getHealth(compositeProductConfig.getCompositeProductUrl());
    }

    public Mono<Health> getConfigServerHealth() {
        return getHealth(compositeProductConfig.getConfigServerUrl());
    }



    private Mono<Health> getHealth(String url) {
        String fullUrl = buildUrl(url + "/actuator/health");
        return webClient.get().uri(fullUrl).retrieve().bodyToMono(String.class)
                .map(s -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
    }

    private String buildUrl(String server) {
        return "http://" + server;
    }

}
