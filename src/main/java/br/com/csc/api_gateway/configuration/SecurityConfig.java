package br.com.csc.api_gateway.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] REFRESH_ROUTE = {"/actuator/gateway/refresh"};

    private static final String[] DISABLE_GATEWAY_ROUTES = {"/actuator/gateway/**"};

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http, ServerOAuth2AuthorizationRequestResolver resolver) {
        http.headers(c -> c.contentTypeOptions(ServerHttpSecurity.HeaderSpec.ContentTypeOptionsSpec::disable)
                        .frameOptions(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec::disable))
                .authorizeExchange(auth -> auth.pathMatchers(REFRESH_ROUTE)
                        .hasAuthority("SCOPE_admin_gateway")
                        .pathMatchers(HttpMethod.DELETE, DISABLE_GATEWAY_ROUTES)
                        .denyAll()
                        .pathMatchers(HttpMethod.POST, DISABLE_GATEWAY_ROUTES)
                        .denyAll()
                        .anyExchange()
                        .permitAll())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        http.oauth2Login(withDefaults());
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public ServerOAuth2AuthorizationRequestResolver pkceResolver(ReactiveClientRegistrationRepository repo) {
        var resolver = new DefaultServerOAuth2AuthorizationRequestResolver(repo);
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return resolver;
    }
}
