package stylepatrick.review.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.review.Review;
import stylepatrick.api.core.review.ReviewService;
import stylepatrick.review.entity.ReviewEntity;
import stylepatrick.review.repository.ReviewRepository;
import stylepatrick.util.services.ServiceUtil;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ServiceUtil serviceUtil;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review createReview(Review review) {
        ReviewEntity entity = reviewMapper.apiToEntity(review);
        reviewRepository.save(entity);
        Review api = reviewMapper.entityToApi(entity);
        api.setServiceAddress(serviceUtil.getServiceAddress());
        return api;
    }

    @Override
    public List<Review> getReviews(Integer productId) {
        List<ReviewEntity> entity = reviewRepository.findByProductId(productId);
        List<Review> reviews = reviewMapper.entityListToApiList(entity);
        reviews.forEach(a -> a.setServiceAddress(serviceUtil.getServiceAddress()));
        return reviews;
    }

    @Override
    public void deleteReviews(Integer productId) {
        reviewRepository.deleteAll(reviewRepository.findByProductId(productId));
    }
}
