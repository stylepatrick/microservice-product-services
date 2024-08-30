package stylepatrick.recommendation.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import stylepatrick.api.core.recommendation.Recommendation;
import stylepatrick.api.core.recommendation.RecommendationService;
import stylepatrick.recommendation.entity.RecommendationEntity;
import stylepatrick.recommendation.repository.RecommendationRepository;
import stylepatrick.util.services.ServiceUtil;

@RestController
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;
    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;

    @Override
    public Mono<Recommendation> createRecommendation(Recommendation recommendation) {
        RecommendationEntity entity = recommendationMapper.apiToEntity(recommendation);
        return recommendationRepository.save(entity)
                .map(recommendationMapper::entityToApi);
    }

    @Override
    public Flux<Recommendation> getRecommendations(Integer productId) {
        return recommendationRepository.findByProductId(productId)
                .map(e -> recommendationMapper.entityToApi(e))
                .map(e -> setServiceAddress(e));
    }

    @Override
    public Mono<Void> deleteRecommendations(Integer productId) {
        return recommendationRepository.deleteAll(recommendationRepository.findByProductId(productId));
    }

    private Recommendation setServiceAddress(Recommendation e) {
        e.setServiceAddress(serviceUtil.getServiceAddress());
        return e;
    }
}
