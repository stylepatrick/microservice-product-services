package stylepatrick.api.core.product;

import org.springframework.web.bind.annotation.*;

public interface ProductService {

    @PostMapping(value = "/product")
    Product createProduct(@RequestBody Product body);

    @GetMapping(value = "/product/{productId}")
    Product getProduct(@PathVariable Integer productId);

    @DeleteMapping(value = "/product/{productId}")
    void deleteProduct(@PathVariable Integer productId);
}
