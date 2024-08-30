package stylepatrick.compositeproduct.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api.common")
public class ApiCommonConfig {

    private String description;
    private String title;
    private String version;
}
