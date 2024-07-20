package stylepatrick.api.core.compositeProduct;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "ProductComposite", description = "REST API for composite product information.")
public interface CompositeProductService {

    @Operation(
            summary = "${api.product-composite.description}",
            description = "${api.product-composite.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok}")
    })
    @GetMapping(value = "/product-composite/{productId}")
    ProductDto getProduct(@PathVariable Integer productId);
}
