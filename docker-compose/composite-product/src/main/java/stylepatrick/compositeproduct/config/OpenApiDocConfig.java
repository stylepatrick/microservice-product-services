package stylepatrick.compositeproduct.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OpenApiDocConfig {

    private final ApiCommonConfig apiCommonConfig;

    @Bean
    public OpenAPI getOpenApiDocumentation() {
        return new OpenAPI()
                .info(new Info().title(apiCommonConfig.getTitle())
                        .description(apiCommonConfig.getDescription())
                        .version(apiCommonConfig.getVersion()));
    }
}
