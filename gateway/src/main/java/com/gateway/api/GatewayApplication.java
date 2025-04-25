package com.gateway.api;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayApplication {
	
	@Value("${patient.url}")
	private String patientUrl;
	@Value("${note.url}")
	private String noteUrl;
	@Value("${risque.url}")
	private String risqueUrl;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		String credentials = Base64.getEncoder().encodeToString("utilisateur:mdp".getBytes());
		return builder.routes()
				.route("patient_route", r -> r
						.path("/patient/**")
						.filters(f -> f
								.addRequestHeader("Authorization", "Basic " + credentials))
						.uri(patientUrl))
				.route("note_route", r -> r
						.path("/note/**")
						.filters(f -> f
								.addRequestHeader("Authorization", "Basic " + credentials))
						.uri(noteUrl))
				.route("risque_route", r -> r
						.path("/risque/**")
						.filters(f -> f
								.addRequestHeader("Authorization", "Basic " + credentials))
						.uri(risqueUrl))
				.build();
	}
}
