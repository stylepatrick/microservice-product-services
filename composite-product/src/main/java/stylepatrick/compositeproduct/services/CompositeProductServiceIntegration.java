package stylepatrick.compositeproduct.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.product.ProductService;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.api.core.review.Review;
import stylepatrick.api.core.review.ReviewService;
import stylepatrick.compositeproduct.config.CompositeProductConfig;

import java.util.List;

@Service
public class CompositeProductServiceIntegration implements ProductService, RecommendationService, ReviewService {

    private final CompositeProductConfig compositeProductConfig;
    private final RestTemplate restTemplate;

    public CompositeProductServiceIntegration(CompositeProductConfig compositeProductConfig) {
        this.compositeProductConfig = compositeProductConfig;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Product getProduct(Integer productId) {
        String url = buildUrl(compositeProductConfig.getProductServiceUrl() + "/product/" + productId);
        return restTemplate.getForObject(url, Product.class);
    }

    @Override
    public List<Recommendation> getRecommendations(Integer productId) {
        String url = buildUrl(compositeProductConfig.getRecommendationServiceUrl() + "/recommendation?productId=" + productId);
        return restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
                })
                .getBody();
    }

    @Override
    public List<Review> getReviews(Integer productId) {
        String url = buildUrl(compositeProductConfig.getReviewServiceUrl() + "/review?productId=" + productId);
        return restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                })
                .getBody();
    }

    private String buildUrl(String server) {
        return "http://" + server;
    }
}
