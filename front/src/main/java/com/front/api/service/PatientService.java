package com.front.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.front.api.model.Patient;

import jakarta.validation.Valid;

@Service
public class PatientService {
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	private RestClient restClient = RestClient.create();
	
	public List<Patient> getPatients() {
		List<Patient> result = restClient.get()
				  .uri(gatewayUrl + "/patient/list")
				  .retrieve()
				  .body(new ParameterizedTypeReference<List<Patient>>() {});
		return result;
	}
	
	public Patient findById(Integer id) {
		Patient result = restClient.get()
				  .uri(gatewayUrl + "/patient/{id}", id)
				  .retrieve()
				  .body(Patient.class);
		return result;
	}

	public void save(Patient patient) {
		ResponseEntity<Void> response = restClient.post()
				  .uri(gatewayUrl + "/patient")
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(patient)
				  .retrieve()
				  .toBodilessEntity();
	}

	public void update(@Valid Patient patient) {
		ResponseEntity<Void> response = restClient.put()
				  .uri(gatewayUrl + "/patient/{id}", patient.getId())
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(patient)
				  .retrieve()
				  .toBodilessEntity();
	}

	public void delete(Integer id) {
		ResponseEntity<Void> response = restClient.delete()
				  .uri(gatewayUrl + "/patient/{id}", id)
				  .retrieve()
				  .toBodilessEntity();
	}
}
