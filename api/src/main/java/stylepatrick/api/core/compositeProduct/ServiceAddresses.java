package stylepatrick.api.core.compositeProduct;

import lombok.Getter;

@Getter
public record ServiceAddresses(String compositeAddress, String productAddress, String reviewAddress,
                               String recommendationAddress) {

}