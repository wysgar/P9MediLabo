package com.front.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RisqueService {
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	private RestClient restClient = RestClient.create();
	
	public String getRisque(Integer id) {
		String result = restClient.get()
				  .uri(gatewayUrl + "/risque/{id}", id)
				  .retrieve()
				  .body(String.class);
		return result;
	}
}
