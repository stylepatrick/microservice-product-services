package stylepatrick.product.services;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import stylepatrick.api.core.product.Product;
import stylepatrick.api.core.product.ProductService;
import stylepatrick.util.services.ServiceUtil;

@RestController
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;

    @Override
    public Product getProduct(Integer productId) {
        return new Product(1, "Product", 5, serviceUtil.getServiceAddress());
    }
}
