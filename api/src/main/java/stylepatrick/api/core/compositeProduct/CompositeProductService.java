package stylepatrick.api.core.compositeProduct;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ProductComposite", description = "REST API for composite product information.")
public interface CompositeProductService {

    @Operation(
            summary = "${api.product-composite.create.description}",
            description = "${api.product-composite.create.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok}"),
    })
    @PostMapping(
            value = "/product-composite")
    void createProduct(@RequestBody ProductDto productDto);

    @Operation(
            summary = "${api.product-composite.get.description}",
            description = "${api.product-composite.get.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok}")
    })
    @GetMapping(value = "/product-composite/{productId}")
    ProductDto getProduct(@PathVariable Integer productId);

    @Operation(
            summary = "${api.product-composite.delete.description}",
            description = "${api.product-composite.delete.product.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok}"),
    })
    @DeleteMapping(value = "/product-composite/{productId}")
    void deleteProduct(@PathVariable Integer productId);
}
