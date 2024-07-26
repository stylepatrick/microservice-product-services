package stylepatrick.product.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
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
    public Product createProduct(Product product) {
        ProductEntity entity = productMapper.apiToEntity(product);
        productRepository.save(entity);
        Product api = productMapper.entityToApi(entity);
        return api;
    }

    @Override
    public Product getProduct(Integer productId) {
        ProductEntity entity = productRepository.findByProductId(productId).orElseThrow(NoSuchElementException::new);
        Product api = productMapper.entityToApi(entity);
        api.setServiceAddress(serviceUtil.getServiceAddress());
        return api;
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.findByProductId(productId)
                .ifPresent(x -> productRepository.deleteById(x.getId()));
    }
}
