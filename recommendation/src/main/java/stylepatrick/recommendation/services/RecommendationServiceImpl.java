package stylepatrick.recommendation.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.recommendation.entity.RecommendationEntity;
import stylepatrick.recommendation.repository.RecommendationRepository;
import stylepatrick.util.services.ServiceUtil;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;
    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;

    @Override
    public Recommendation createRecommendation(Recommendation recommendation) {
        RecommendationEntity entity = recommendationMapper.apiToEntity(recommendation);
        recommendationRepository.save(entity);
        Recommendation api = recommendationMapper.entityToApi(entity);
        return api;
    }

    @Override
    public List<Recommendation> getRecommendations(Integer productId) {
        List<RecommendationEntity> entity = recommendationRepository.findByProductId(productId);
        List<Recommendation> recommendations = recommendationMapper.entityListToApiList(entity);
        recommendations.forEach(a -> a.setServiceAddress(serviceUtil.getServiceAddress()));
        return recommendations;
    }

    @Override
    public void deleteRecommendations(Integer productId) {
        recommendationRepository.deleteAll(recommendationRepository.findByProductId(productId));
    }
}
