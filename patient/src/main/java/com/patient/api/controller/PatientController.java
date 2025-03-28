package com.patient.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.patient.api.User;
import com.patient.api.model.Patient;
import com.patient.api.repository.PatientRepository;
import com.patient.api.service.PatientService;


@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	@GetMapping
	public List<Patient> getPatient() {
		return patientService.getPatient();
	}
	
	@PostMapping
	private void addPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
	}
	
	@PutMapping
	public void updatePatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
	}
	
	@Bean
	public CommandLineRunner initDatabase(PatientRepository patientRepository) {
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
	            patient2.setFirstName("");
	            patient2.setBirthday("");
	            patient2.setGender("");
	            patient2.setAddress("");
	            patient2.setPhone("");
	            
	            Patient patient3 = new Patient();
	            patient3.setLastName("");
	            patient3.setFirstName("");
	            patient3.setBirthday("");
	            patient3.setGender("");
	            patient3.setAddress("");
	            patient3.setPhone("");
	            
	            Patient patient4 = new Patient();
	            patient4.setLastName("");
	            patient4.setFirstName("");
	            patient4.setBirthday("");
	            patient4.setGender("");
	            patient4.setAddress("");
	            patient4.setPhone("");

	            patientRepository.save(patient);
	            patientRepository.save(patient2);
	            patientRepository.save(patient3);
	            patientRepository.save(patient4);

	            System.out.println("Admin and User accounts have been created.");
	        } else {
	            System.out.println("Users already exist, skipping initialization.");
	        }
	    };
	}
}
