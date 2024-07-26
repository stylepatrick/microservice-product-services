package stylepatrick.api.core.recommendation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RecommendationService {

    @PostMapping(value = "/recommendation")
    Recommendation createRecommendation(@RequestBody Recommendation body);

    @GetMapping(value = "/recommendation")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId") Integer productId);

    @DeleteMapping(value = "/recommendation")
    void deleteRecommendations(@RequestParam(value = "productId") Integer productId);
}
