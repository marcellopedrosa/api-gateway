// package br.com.csc.api_gateway.configuration;

// import org.apache.commons.configuration.PropertiesConfiguration;
// import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
// import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.Environment;

// @Configuration
// public class RefreshRouteConfig {

//     @Bean
//     public RouteDefinitionLocator propertiesRouteDefinitionLocator(Environment environment) {
//         return new PropertiesRouteDefinitionLocator(new PropertiesConfiguration(environment));
//     }
// }
