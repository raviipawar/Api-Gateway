package com.learning.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-converter-new/**")
						.filters(f -> f.rewritePath("currency-converter-new/(?<segment>.*)", "currency-converter-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-converter-feign/**").uri("lb://currency-conversion")).build();
	}
}
