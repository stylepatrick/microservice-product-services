package stylepatrick.product.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.product.ProductService;
import stylepatrick.product.entity.ProductEntity;
import stylepatrick.product.repository.ProductRepository;
import stylepatrick.util.services.ServiceUtil;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<Product> createProduct(Product product) {
        ProductEntity entity = productMapper.apiToEntity(product);
        return productRepository.save(entity)
                .map(productMapper::entityToApi);
    }

    @Override
    public Mono<Product> getProduct(Integer productId) {
        Mono<Product> api = productRepository.findByProductId(productId)
                .switchIfEmpty(Mono.error(new NoSuchElementException()))
                .map(productMapper::entityToApi)
                .map(e -> {
                    e.setServiceAddress(serviceUtil.getServiceAddress());
                    return e;
                });
        return api;
    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        return productRepository.findByProductId(productId)
                .map(e -> productRepository.deleteById(e.getId()))
                .flatMap(e -> e);
    }
}
