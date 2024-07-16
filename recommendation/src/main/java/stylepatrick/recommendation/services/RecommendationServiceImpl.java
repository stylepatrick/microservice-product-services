package stylepatrick.recommendation.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.util.services.ServiceUtil;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;

    @Override
    public List<Recommendation> getRecommendations(Integer productId) {
        return List.of(
                new Recommendation(1, 1, "Test", 5, "Test", serviceUtil.getServiceAddress()),
                new Recommendation(1, 2, "Test", 3, "Test", serviceUtil.getServiceAddress())
        );
    }
}
