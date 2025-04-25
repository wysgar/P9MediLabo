package com.risque.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.risque.api.model.Patient;

@Service
public class InfoService {

	@Value("${gateway.url}")
	private String gatewayUrl;
	
	private RestClient restClient = RestClient.create();
	
	public Patient findById(Integer id) {
		Patient result = restClient.get()
				  .uri(gatewayUrl + "/patient/{id}", id)
				  .retrieve()
				  .body(Patient.class);
		return result;
	}
}
