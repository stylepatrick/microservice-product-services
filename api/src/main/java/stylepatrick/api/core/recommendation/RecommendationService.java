package stylepatrick.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecommendationService {

    @GetMapping(value = "/recommendation")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId") Integer productId);
}
