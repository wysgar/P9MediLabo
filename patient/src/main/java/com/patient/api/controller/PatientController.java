package com.patient.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.patient.api.model.Patient;
import com.patient.api.repository.PatientRepository;
import com.patient.api.service.PatientService;


@Configuration
@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	@GetMapping("/list")
	public List<Patient> getPatient() {
		return patientService.getPatient();
	}
	
	@GetMapping("/{id}")
	public Optional<Patient> getPatientById(@PathVariable Integer id) {
		return patientService.getPatientById(id);
	}
	
	@PostMapping
	private ResponseEntity<Object> addPatient(@RequestBody Patient patient) {
		Patient patientAdded = patientService.savePatient(patient);
		if (Objects.isNull(patientAdded)) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(patientAdded.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public void updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
		patientService.savePatient(patient);
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Integer id) {
		patientService.deletePatient(id);
	}

    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository) {
	    return args -> {
	        if (patientRepository.count() == 0) {
	            Patient patient = new Patient();
	            patient.setLastName("TestNone");
	            patient.setFirstName("Test");
	            patient.setBirthday("1966-12-31");
	            patient.setGender("F");
	            patient.setAddress("1 Brookside St");
	            patient.setPhone("100-222-3333");
	            
	            Patient patient2 = new Patient();
	            patient2.setLastName("TestBorderline");
	            patient2.setFirstName("Test");
	            patient2.setBirthday("1945-06-24");
	            patient2.setGender("M");
	            patient2.setAddress("2 High St");
	            patient2.setPhone("200-333-4444");
	            
	            Patient patient3 = new Patient();
	            patient3.setLastName("TestInDanger");
	            patient3.setFirstName("Test");
	            patient3.setBirthday("2004-06-18");
	            patient3.setGender("M");
	            patient3.setAddress("3 Club Road");
	            patient3.setPhone("300-444-5555");
	            
	            Patient patient4 = new Patient();
	            patient4.setLastName("TestEarlyOnset");
	            patient4.setFirstName("Test");
	            patient4.setBirthday("2002-06-28");
	            patient4.setGender("F");
	            patient4.setAddress("4 Valley Dr");
	            patient4.setPhone("400-555-6666");

	            patientRepository.save(patient);
	            patientRepository.save(patient2);
	            patientRepository.save(patient3);
	            patientRepository.save(patient4);

	            System.out.println("Patients Created");
	        } else {
	            System.out.println("Patients already exist, skipping initialization.");
	        }
	    };
	}
}
