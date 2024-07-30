package stylepatrick.compositeproduct.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import stylepatrick.api.core.compositeProduct.CompositeProductService;
import stylepatrick.api.core.compositeProduct.ProductDto;
import stylepatrick.api.core.compositeProduct.ServiceAddresses;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.review.Review;
import stylepatrick.util.services.ServiceUtil;

import java.util.List;

@RestController
@AllArgsConstructor
public class CompositeProductServiceImpl implements CompositeProductService {

    private final CompositeProductServiceIntegration compositeProductServiceIntegration;
    private final ServiceUtil serviceUtil;

    @Override
    public Mono<Void> createProduct(ProductDto productDto) {
        compositeProductServiceIntegration.createProduct(productDto.product()).subscribe();
        if (!productDto.recommendations().isEmpty()) {
            productDto.recommendations().forEach(e -> compositeProductServiceIntegration.createRecommendation(e).subscribe());
        }
        if (!productDto.reviews().isEmpty()) {
            productDto.reviews().forEach(e -> compositeProductServiceIntegration.createReview(e).subscribe());
        }
        return Mono.empty();
    }

    @Override
    public Mono<ProductDto> getProduct(Integer productId) {
        return Mono.zip(
                values -> createProductDto((Product) values[0], (List<Recommendation>) values[1], (List<Review>) values[2], serviceUtil.getServiceAddress()),
                compositeProductServiceIntegration.getProduct(productId),
                compositeProductServiceIntegration.getRecommendations(productId).collectList(),
                compositeProductServiceIntegration.getReviews(productId).collectList());
    }

    private ProductDto createProductDto(Product product, List<Recommendation> recommendations, List<Review> reviews, String serviceAddress) {

        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && !reviews.isEmpty()) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommendations != null && !recommendations.isEmpty()) ? recommendations.get(0).getServiceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress);

        return new ProductDto(product, recommendations, reviews, serviceAddresses);
    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        compositeProductServiceIntegration.deleteProduct(productId).subscribe();
        compositeProductServiceIntegration.deleteReviews(productId).subscribe();
        compositeProductServiceIntegration.deleteRecommendations(productId).subscribe();
        return Mono.empty();
    }
}
