package stylepatrick.api.core.compositeProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CompositeProductService {

    @GetMapping(value = "/product-composite/{productId}")
    ProductDto getProduct(@PathVariable Integer productId);
}
