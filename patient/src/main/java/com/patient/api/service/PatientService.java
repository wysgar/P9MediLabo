package com.patient.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.api.model.Patient;
import com.patient.api.repository.PatientRepository;


@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> getPatient() {
		return patientRepository.findAll();
	}

	public void savePatient(Patient patient) {
		patientRepository.save(patient);
	}

}
