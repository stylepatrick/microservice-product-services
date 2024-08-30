package stylepatrick.review.repository;

import org.springframework.data.repository.CrudRepository;
import stylepatrick.review.entity.ReviewEntity;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByProductId(int productId);
}