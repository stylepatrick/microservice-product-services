package stylepatrick.compositeproduct.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.product.ProductService;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.api.core.review.Review;
import stylepatrick.api.core.review.ReviewService;
import stylepatrick.compositeproduct.config.CompositeProductConfig;

@Service
public class CompositeProductServiceIntegration implements ProductService, RecommendationService, ReviewService {

    private final CompositeProductConfig compositeProductConfig;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public CompositeProductServiceIntegration(
            CompositeProductConfig compositeProductConfig,
            WebClient.Builder webClient
    ) {
        this.compositeProductConfig = compositeProductConfig;
        this.webClient = webClient.build();
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product");
        return webClient.post()
                .uri(url)
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(Product.class);
    }


    @Override
    public Mono<Product> getProduct(Integer productId) {
        String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product/" + productId);
        return webClient.get().uri(url).retrieve().bodyToMono(Product.class);
    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product/" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);
    }

    @Override
    public Mono<Recommendation> createRecommendation(Recommendation body) {
        String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation");
        return webClient.post().uri(url).body(BodyInserters.fromValue(body)).retrieve().bodyToMono(Recommendation.class);
    }

    @Override
    public Flux<Recommendation> getRecommendations(Integer productId) {
        String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation?productId=" + productId);
        return webClient.get().uri(url).retrieve().bodyToFlux(Recommendation.class);
    }

    @Override
    public Mono<Void> deleteRecommendations(Integer productId) {
        String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation?productId=" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);
    }

    @Override
    public Mono<Review> createReview(Review body) {
        String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review");
        return webClient.post().uri(url).body(BodyInserters.fromValue(body)).retrieve().bodyToMono(Review.class);
    }

    @Override
    public Flux<Review> getReviews(Integer productId) {
        String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review?productId=" + productId);
        return webClient.get().uri(url).retrieve().bodyToFlux(Review.class);
    }

    @Override
    public Mono<Void> deleteReviews(Integer productId) {
        String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review?productId=" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);
    }


    private String buildUrl(String server) {
        return "http://" + server;
    }
}
