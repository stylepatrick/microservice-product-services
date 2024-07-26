package stylepatrick.recommendation.repository;

import org.springframework.data.repository.CrudRepository;
import stylepatrick.recommendation.entity.RecommendationEntity;

import java.util.List;

public interface RecommendationRepository extends CrudRepository<RecommendationEntity, String> {
    List<RecommendationEntity> findByProductId(Integer productId);
}
