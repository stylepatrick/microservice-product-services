package stylepatrick.product.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import stylepatrick.api.core.product.Product;
import stylepatrick.product.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "serviceAddress", ignore = true)
    })
    Product entityToApi(ProductEntity entity);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    ProductEntity apiToEntity(Product api);

}
