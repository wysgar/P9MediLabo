package com.risque.api.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.risque.api.model.Note;

@Service
public class NoteService {
	private RestClient restClient = RestClient.create();

	public List<Note> getNoteByPatient(Integer id) {
		List<Note> result = restClient.get()
				  .uri("http://localhost:8084/note/{id}", id)
				  .retrieve()
				  .body(new ParameterizedTypeReference<List<Note>>() {});
		return result;
	}
}
