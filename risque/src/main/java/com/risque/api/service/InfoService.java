package com.risque.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.risque.api.model.Patient;

@Service
public class InfoService {

	private RestClient restClient = RestClient.create();
	
	public Patient findById(Integer id) {
		Patient result = restClient.get()
				  .uri("http://localhost:8084/patient/{id}", id)
				  .retrieve()
				  .body(Patient.class);
		return result;
	}
}
