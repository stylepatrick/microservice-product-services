package stylepatrick.api.core.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int productId;
    private String name;
    private int weight;
    private String serviceAddress;

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

}
