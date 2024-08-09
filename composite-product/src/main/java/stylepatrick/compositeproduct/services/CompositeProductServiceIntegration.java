package stylepatrick.compositeproduct.services;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import stylepatrick.api.core.event.Event;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.product.ProductService;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.api.core.review.Review;
import stylepatrick.api.core.review.ReviewService;
import stylepatrick.compositeproduct.config.CompositeProductConfig;

import static stylepatrick.api.core.event.Event.Type.CREATE;
import static stylepatrick.api.core.event.Event.Type.DELETE;

@Service
public class CompositeProductServiceIntegration implements ProductService, RecommendationService, ReviewService {

    private final CompositeProductConfig compositeProductConfig;
    private final WebClient webClient;
    private final StreamBridge streamBridge;

    public CompositeProductServiceIntegration(
            CompositeProductConfig compositeProductConfig,
            WebClient.Builder webClientBuilder,
            StreamBridge streamBridge
    ) {
        this.compositeProductConfig = compositeProductConfig;
        this.webClient = webClientBuilder.build();
        this.streamBridge = streamBridge;
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        /*String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product");
        return webClient.post()
                .uri(url)
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(Product.class);*/
        return Mono.fromCallable(() -> {
            sendMessage("products-out-0", new Event<>(CREATE, product.getProductId(), product));
            return product;
        });
    }


    @Override
    public Mono<Product> getProduct(Integer productId) {
        String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product/" + productId);
        return webClient.get().uri(url).retrieve().bodyToMono(Product.class);
    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        /*String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product/" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);*/
        return Mono.fromRunnable(() -> sendMessage("products-out-0", new Event<>(DELETE, productId, null)))
                .then();
    }

    @Override
    public Mono<Recommendation> createRecommendation(Recommendation recommendation) {
        /*String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation");
        return webClient.post().uri(url).body(BodyInserters.fromValue(recommendation)).retrieve().bodyToMono(Recommendation.class);*/
        return Mono.fromCallable(() -> {
            sendMessage("recommendations-out-0", new Event<>(CREATE, recommendation.getProductId(), recommendation));
            return recommendation;
        });
    }

    @Override
    public Flux<Recommendation> getRecommendations(Integer productId) {
        String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation?productId=" + productId);
        return webClient.get().uri(url).retrieve().bodyToFlux(Recommendation.class);
    }

    @Override
    public Mono<Void> deleteRecommendations(Integer productId) {
        /*String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation?productId=" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);*/
        return Mono.fromRunnable(() -> sendMessage("recommendations-out-0", new Event<>(DELETE, productId, null))).then();
    }

    @Override
    public Mono<Review> createReview(Review review) {
        /*String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review");
        return webClient.post().uri(url).body(BodyInserters.fromValue(body)).retrieve().bodyToMono(Review.class);*/
        return Mono.fromCallable(() -> {
            sendMessage("reviews-out-0", new Event<>(CREATE, review.getProductId(), review));
            return review;
        });
    }

    @Override
    public Flux<Review> getReviews(Integer productId) {
        String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review?productId=" + productId);
        return webClient.get().uri(url).retrieve().bodyToFlux(Review.class);
    }

    @Override
    public Mono<Void> deleteReviews(Integer productId) {
        /*String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review?productId=" + productId);
        return webClient.delete().uri(url).retrieve().bodyToMono(Void.class);*/
        return Mono.fromRunnable(() -> sendMessage("reviews-out-0", new Event<>(DELETE, productId, null))).then();

    }


    private String buildUrl(String server) {
        return "http://" + server;
    }

    private void sendMessage(String bindingName, Event event) {
        Message message = MessageBuilder.withPayload(event)
                .build();
        streamBridge.send(bindingName, message);
    }
}
