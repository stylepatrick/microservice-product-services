package stylepatrick.api.core.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {

    @GetMapping(value = "/review")
    List<Review> getReviews(@RequestParam(value = "productId") Integer productId);
}
