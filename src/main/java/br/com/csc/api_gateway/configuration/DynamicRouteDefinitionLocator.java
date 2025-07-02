package br.com.csc.api_gateway.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * Force refresh routes manually
 */
@RefreshScope
@Component
public class DynamicRouteDefinitionLocator implements RouteDefinitionLocator {

    private final Environment environment;

    public DynamicRouteDefinitionLocator(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        // Lê as rotas da configuração atual do Environment
        Binder binder = Binder.get(environment);
        List<RouteDefinition> routes = binder
                .bind("spring.cloud.gateway.routes", Bindable.listOf(RouteDefinition.class))
                .orElse(Collections.emptyList());
        return Flux.fromIterable(routes);
    }
}