package com.front.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RisqueService {
	private RestClient restClient = RestClient.create();
	
	public String getRisque(Integer id) {
		String result = restClient.get()
				  .uri("http://localhost:8084/risque/{id}", id)
				  .retrieve()
				  .body(String.class);
		return result;
	}
}
