package stylepatrick.review.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.review.Review;
import stylepatrick.api.core.review.ReviewService;
import stylepatrick.util.services.ServiceUtil;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ServiceUtil serviceUtil;

    @Override
    public List<Review> getReviews(Integer productId) {
        return List.of(
                new Review(1, 1, "Test", "", "", serviceUtil.getServiceAddress()),
                new Review(1, 2, "Test", "", "", serviceUtil.getServiceAddress())
        );
    }
}
