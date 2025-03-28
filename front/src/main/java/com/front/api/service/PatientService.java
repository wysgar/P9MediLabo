package com.front.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
	private void httpget() {
		// TODO Auto-generated method stub
		String result = restClient.get()
				  .uri("localhost:8080/patient")
				  .retrieve()
				  .body(String.class);
	}
}
