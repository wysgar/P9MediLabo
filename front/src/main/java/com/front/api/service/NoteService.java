package com.front.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.front.api.model.Note;

@Service
public class NoteService {
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	private RestClient restClient = RestClient.create();

	public List<Note> getNoteByPatient(Integer id) {
		List<Note> result = restClient.get()
				  .uri(gatewayUrl + "/note/{id}", id)
				  .retrieve()
				  .body(new ParameterizedTypeReference<List<Note>>() {});
		return result;
	}

	public void save(Note note) {
		ResponseEntity<Void> response = restClient.post()
				  .uri(gatewayUrl + "/note")
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(note)
				  .retrieve()
				  .toBodilessEntity();
	}
}
