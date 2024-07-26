package stylepatrick.api.core.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewService {

    @PostMapping(value = "/review")
    Review createReview(@RequestBody Review body);

    @GetMapping(value = "/review")
    List<Review> getReviews(@RequestParam(value = "productId") Integer productId);

    @DeleteMapping(value = "/review")
    void deleteReviews(@RequestParam(value = "productId") Integer productId);
}
