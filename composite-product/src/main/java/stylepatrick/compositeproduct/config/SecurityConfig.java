package stylepatrick.compositeproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import stylepatrick.compositeproduct.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public Jwt2AuthoritiesConverter authoritiesConverter() {
        // This is a converter for roles as embedded in the JWT by a Keycloak server
        // Roles are taken from both realm_access.roles
        return jwt -> {
            final var realmAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("realm_access", Map.of());
            final var realmRoles = (Collection<String>) realmAccess.getOrDefault("roles", List.of());

            return realmRoles.stream().filter(UserRole::isValidRole)
                    .map(SimpleGrantedAuthority::new).toList();
        };
    }

    @Bean
    public Jwt2AuthenticationConverter authenticationConverter(Jwt2AuthoritiesConverter authoritiesConverter) {
        return jwt -> new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt));
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, Converter<Jwt, AbstractAuthenticationToken> authenticationConverter) {
        Converter<Jwt, Mono<AbstractAuthenticationToken>> reactiveJwtAuthenticationConverter = new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter);
        http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/openapi/**").permitAll()
                        .pathMatchers("/webjars/**").permitAll()
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/product-composite/**")
                        .hasAnyAuthority(UserRole.WRITE.getValue())
                        .pathMatchers(HttpMethod.POST, "/product-composite/**")
                        .hasAnyAuthority(UserRole.WRITE.getValue())
                        .pathMatchers(HttpMethod.GET, "/product-composite/**")
                        .hasAnyAuthority(UserRole.READ.getValue(), UserRole.WRITE.getValue())
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(reactiveJwtAuthenticationConverter))
                );
        return http.build();
    }

    public interface Jwt2AuthoritiesConverter extends Converter<Jwt, Collection<? extends GrantedAuthority>> {
    }

    public interface Jwt2AuthenticationConverter extends Converter<Jwt, AbstractAuthenticationToken> {
    }

}
