package stylepatrick.review.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
    public Mono<Review> createReview(Review review) {
        return Mono.fromCallable(() -> internalCreateReview(review));
    }

    private Review internalCreateReview(Review review) {
        ReviewEntity entity = reviewMapper.apiToEntity(review);
        reviewRepository.save(entity);
        Review api = reviewMapper.entityToApi(entity);
        api.setServiceAddress(serviceUtil.getServiceAddress());
        return api;
    }

    @Override
    public Flux<Review> getReviews(Integer productId) {
        return Mono.fromCallable(() -> internalGetReviews(productId))
                .flatMapMany(Flux::fromIterable);
    }

    private List<Review> internalGetReviews(Integer productId) {
        List<ReviewEntity> entityList = reviewRepository.findByProductId(productId);
        List<Review> list = reviewMapper.entityListToApiList(entityList);
        list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));
        return list;
    }

    @Override
    public Mono<Void> deleteReviews(Integer productId) {
        return Mono.fromRunnable(() -> internalDeleteReviews(productId));
    }

    private void internalDeleteReviews(Integer productId) {
        reviewRepository.deleteAll(reviewRepository.findByProductId(productId));
    }
}
