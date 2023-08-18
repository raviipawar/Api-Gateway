package com.learning.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/currency-exchanges/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-converter-feign/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/user-service/all/**").uri("lb://user-service")).build();
	}
}
