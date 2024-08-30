package stylepatrick.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationService {

    //@PostMapping(value = "/recommendation")
    Mono<Recommendation> createRecommendation(@RequestBody Recommendation body);

    @GetMapping(value = "/recommendation")
    Flux<Recommendation> getRecommendations(@RequestParam(value = "productId") Integer productId);

    //@DeleteMapping(value = "/recommendation")
    Mono<Void> deleteRecommendations(@RequestParam(value = "productId") Integer productId);
}
