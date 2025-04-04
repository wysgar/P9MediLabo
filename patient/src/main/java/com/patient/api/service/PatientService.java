package com.patient.api.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Patient> getPatientById(Integer id) {
		return patientRepository.findById(id);
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public void deletePatient(Integer id) {
		patientRepository.deleteById(id);
	}

}
