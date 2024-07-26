package stylepatrick.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import stylepatrick.product.entity.ProductEntity;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, String>, PagingAndSortingRepository<ProductEntity, String> {
    Optional<ProductEntity> findByProductId(Integer productId);

}
