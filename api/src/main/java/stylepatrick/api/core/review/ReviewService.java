package stylepatrick.api.core.review;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {

    @PostMapping(value = "/review")
    Mono<Review> createReview(@RequestBody Review body);

    @GetMapping(value = "/review")
    Flux<Review> getReviews(@RequestParam(value = "productId") Integer productId);

    @DeleteMapping(value = "/review")
    Mono<Void> deleteReviews(@RequestParam(value = "productId") Integer productId);
}
