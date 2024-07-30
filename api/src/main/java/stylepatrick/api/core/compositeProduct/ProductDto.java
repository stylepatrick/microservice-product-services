package stylepatrick.api.core.compositeProduct;

import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.review.Review;

import java.util.List;

public record ProductDto(Product product, List<Recommendation> recommendations, List<Review> reviews,
                         ServiceAddresses serviceAddresses) {
}
