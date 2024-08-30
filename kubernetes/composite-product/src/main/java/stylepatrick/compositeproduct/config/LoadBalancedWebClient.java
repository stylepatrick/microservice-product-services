package stylepatrick.compositeproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LoadBalancedWebClient {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
