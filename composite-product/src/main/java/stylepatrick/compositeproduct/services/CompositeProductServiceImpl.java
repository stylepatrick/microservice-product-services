package stylepatrick.compositeproduct.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.compositeProduct.CompositeProductService;
import stylepatrick.api.core.compositeProduct.ProductDto;
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
    public void createProduct(ProductDto productDto) {
        compositeProductServiceIntegration.createProduct(productDto.product());
        if (!productDto.recommendations().isEmpty()) {
            productDto.recommendations().forEach(compositeProductServiceIntegration::createRecommendation);
        }
        if (!productDto.reviews().isEmpty()) {
            productDto.reviews().forEach(compositeProductServiceIntegration::createReview);
        }
    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Product product = compositeProductServiceIntegration.getProduct(productId);
        List<Recommendation> recommendations = compositeProductServiceIntegration.getRecommendations(productId);
        List<Review> reviews = compositeProductServiceIntegration.getReviews(productId);
        return new ProductDto(product, recommendations, reviews, serviceUtil.getServiceAddress());
    }

    @Override
    public void deleteProduct(Integer productId) {
        compositeProductServiceIntegration.deleteProduct(productId);
        compositeProductServiceIntegration.deleteReviews(productId);
        compositeProductServiceIntegration.deleteRecommendations(productId);
    }
}
