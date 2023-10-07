package br.com.herlandio7cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Herlandio7CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Herlandio7CloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/clients/**").uri("lb://herlandio7-client-service")) // pegar nome no
				.route(r -> r.path("/cards/**").uri("lb://herlandio7-card-service")) // application.yml
				.route(r -> r.path("/credit-ratings/**").uri("lb://herlandio7-credit-assessor-service"))
				.build();
	}

}
