package stylepatrick.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface ProductService {

    //@PostMapping(value = "/product")
    Mono<Product> createProduct(@RequestBody Product body);

    @GetMapping(value = "/product/{productId}")
    Mono<Product> getProduct(@PathVariable Integer productId);

    //@DeleteMapping(value = "/product/{productId}")
    Mono<Void> deleteProduct(@PathVariable Integer productId);
}
